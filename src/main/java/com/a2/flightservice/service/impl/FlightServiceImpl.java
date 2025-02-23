package com.a2.flightservice.service.impl;


import com.a2.flightservice.client.ticketService.SoldTicketsDto;
import com.a2.flightservice.domain.Flight;
import com.a2.flightservice.domain.Plane;
import com.a2.flightservice.dto.FlightCancelDto;
import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.exception.NotFoundException;
import com.a2.flightservice.mapper.FlightMapper;
import com.a2.flightservice.repository.FlightRepository;
import com.a2.flightservice.repository.PlaneRepository;
import com.a2.flightservice.service.FlightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.springframework.data.jpa.domain.Specification;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final PlaneRepository planeRepository;
    private final FlightRepository flightRepository;

    private final FlightMapper flightMapper;
    private final ObjectMapper objectMapper;

    private final JmsTemplate jmsTemplate;
    private final String destinationCancelFlight;

    private RestTemplate ticketServiceRestTemplate;

    public FlightServiceImpl(PlaneRepository planeRepository,
                             FlightRepository flightRepository,
                             FlightMapper flightMapper,
                             ObjectMapper objectMapper,
                             JmsTemplate jmsTemplate,
            RestTemplate ticketServiceRestTemplate,
                             @Value("${destination.cancel-flight}") String destinationCancelFlight) {
        this.planeRepository = planeRepository;
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.destinationCancelFlight = destinationCancelFlight;
        this.ticketServiceRestTemplate = ticketServiceRestTemplate;
    }

    @Override
    public FlightDto addFlight(FlightCreateDto flightCreateDto) {
        Flight flight = new Flight();
        Plane plane = planeRepository.findByPlaneId(flightCreateDto.getPlaneId())
                .orElseThrow(() -> new NotFoundException(String.format("Plane with id: %d not found.", flightCreateDto.getPlaneId())));
        flight.setStartDestination(flightCreateDto.getStartDestination());
        flight.setEndDestination(flightCreateDto.getEndDestination());
        flight.setMiles(flightCreateDto.getMiles());
        flight.setPrice(flightCreateDto.getPrice());
        flight.setPlane(plane);
        flight.setFlightStatus("ACTIVE");
        Flight rtn = flightRepository.save(flight);
        return flightMapper.flightToFlightDto(rtn);
    }


    @Override
    public void cancelFlight(Long flightId) {
        Flight flight = flightRepository.findByFlightId(flightId)
                .orElseThrow(() -> new NotFoundException(String.format("Flight with id: %d not found.", flightId)));
        FlightCancelDto flightCancelDto = new FlightCancelDto(flight.getFlightId(), flight.getMiles());
        ResponseEntity<SoldTicketsDto> responseEntitySoldTicketsDto = null;
        try{
            responseEntitySoldTicketsDto = ticketServiceRestTemplate.exchange("/ticket/flight/" + flight.getFlightId(), HttpMethod.GET, null, SoldTicketsDto.class);

        }catch (HttpClientErrorException e){
            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                throw  new NotFoundException(String.format("Flight with id: %d not found.",flight.getFlightId()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(responseEntitySoldTicketsDto.getBody().getSoldTickets() + "SOLD TICKETS");
        if (responseEntitySoldTicketsDto.getBody().getSoldTickets()==0){
            flightRepository.deleteByFlightId(flightId);
        }else{
            flight.setFlightStatus("CANCELED");
            flightRepository.save(flight);
            try {
                jmsTemplate.convertAndSend(destinationCancelFlight, objectMapper.writeValueAsString(flightCancelDto));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Page<FlightDto> findAllAvailableFlights(Pageable pageable) {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDto> flightDtos = new ArrayList<>();
        for( Flight flight : flights){
            ResponseEntity<SoldTicketsDto> responseEntitySoldTicketsDto = null;
            try{
                responseEntitySoldTicketsDto = ticketServiceRestTemplate.exchange("/ticket/flight/" + flight.getFlightId(), HttpMethod.GET, null, SoldTicketsDto.class);
            }catch (HttpClientErrorException e){
                if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                    throw  new NotFoundException(String.format("Flight with id: %d not found.",flight.getFlightId()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(responseEntitySoldTicketsDto+ " get sold");
            if(responseEntitySoldTicketsDto.getBody().getSoldTickets() < flight.getPlane().getCapacity()
                && flight.getFlightStatus().equals("ACTIVE")){
                flightDtos.add(flightMapper.flightToFlightDto(flight));
            }
        }
        int start = (int) pageable.getOffset();
        int end = (start+pageable.getPageSize())> flightDtos.size() ? flightDtos.size() : (start+pageable.getPageSize());
        Page<FlightDto> toReturn = new PageImpl<>(flightDtos.subList(start,end), pageable, flightDtos.size());
        return toReturn;

    }

    @Override
    public Page<FlightDto> searchFlights(Specification<FlightDto> where, Pageable pageable) {
        List<Flight> flights = flightRepository.findAll((Sort) Specification.where(where));
        List<FlightDto> flightDtos = flightMapper.flightsToFlightsDto(flights);
        int start = (int) pageable.getOffset();
        int end = (start+pageable.getPageSize())> flightDtos.size() ? flightDtos.size() : (start+pageable.getPageSize());
        Page<FlightDto> toReturn = new PageImpl<>(flightDtos.subList(start,end), pageable, flightDtos.size());
        return toReturn;
    }

    @Override
    public FlightDto findById(Long flightId) {
        Flight flight =  flightRepository.findByFlightId(flightId)
                .orElseThrow(() -> new NotFoundException(String.format("Flight with id: %d not found.", flightId)));
        return flightMapper.flightToFlightDto(flight);
    }

    @Override
    public Long getCountOfFlights() {
        return flightRepository.count();
    }

    @Override
    public List<FlightDto> findAllAvailableFlightsList() {
        return flightRepository.findAll().stream().map(flightMapper::flightToFlightDto).collect(Collectors.toList());
    }

}