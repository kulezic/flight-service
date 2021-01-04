package com.a2.flightservice.service.impl;


import com.a2.flightservice.client.ticketService.SoldTicketsDto;
import com.a2.flightservice.domain.Flight;
import com.a2.flightservice.domain.Plane;
import com.a2.flightservice.dto.FlightCancelDto;
import com.a2.flightservice.dto.FlightCapacityDto;
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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    private PlaneRepository planeRepository;
    private FlightRepository flightRepository;

    private FlightMapper flightMapper;
    private ObjectMapper objectMapper;

    private JmsTemplate jmsTemplate;
    private String destinationCancelFlight;

    private RestTemplate ticketServiceRestTemplate;

    public FlightServiceImpl(PlaneRepository planeRepository,
                             FlightRepository flightRepository,
                             FlightMapper flightMapper,
                             ObjectMapper objectMapper,
                             JmsTemplate jmsTemplate,
                             @Value("${destination.cancel-flight}") String destinationCancelFlight) {
        this.planeRepository = planeRepository;
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.destinationCancelFlight = destinationCancelFlight;
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
        Flight rtn = flightRepository.save(flight);
        return flightMapper.flightToFlightDto(rtn);
    }


    @Override
    public void cancelFlight(Long flightId) {
        Flight flight = flightRepository.findByFlightId(flightId)
                .orElseThrow(() -> new NotFoundException(String.format("Flight with id: %d not found.", flightId)));
        FlightCancelDto flightCancelDto = new FlightCancelDto(flight.getFlightId(), flight.getMiles());
        //TODO Flight status canceled
        try {
            jmsTemplate.convertAndSend(destinationCancelFlight, objectMapper.writeValueAsString(flightCancelDto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<FlightDto> findAllAvailableFlights(Pageable pageable) {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDto> flightDtos = new ArrayList<>();
        for( Flight flight : flights){
            ResponseEntity<SoldTicketsDto> responseEntitySoldTicketsDto = null;
            try{
                responseEntitySoldTicketsDto = ticketServiceRestTemplate.exchange("/flight/" + flight.getFlightId(), HttpMethod.GET, null, SoldTicketsDto.class);
            }catch (HttpClientErrorException e){
                if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
                    throw  new NotFoundException(String.format("Flight with id: %d not found.",flight.getFlightId()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            if(responseEntitySoldTicketsDto.getBody().getSoldTickets() < flight.getPlane().getCapacity()){
                flightDtos.add(flightMapper.flightToFlightDto(flight));
            }
        }
        int start = (int) pageable.getOffset();
        int end = (start+pageable.getPageSize())> flightDtos.size() ? flightDtos.size() : (start+pageable.getPageSize());
        Page<FlightDto> toReturn = new PageImpl<>(flightDtos.subList(start,end), pageable, flightDtos.size());
        return toReturn;

    }

    @Override
    public Page<FlightDto> searchFlights() {
        return null;
    }

    @Override
    public FlightCapacityDto findFlightCapacity(Long flightId) {
        Flight flight = flightRepository.findByFlightId(flightId)
                .orElseThrow(() -> new NotFoundException(String.format("Flight with id: %d not found.", flightId)));
        return new FlightCapacityDto(flight.getPlane().getCapacity());
    }
}