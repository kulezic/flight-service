package com.a2.flightservice.service.implementation;

import com.a2.flightservice.dto.FlightCapacityDto;
import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.a2.flightservice.dto.FlightCancelDto;
import com.a2.flightservice.exception.NotFoundException;
import com.a2.flightservice.domain.Flight;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import com.a2.flightservice.repository.PlaneRepository;
import com.a2.flightservice.repository.FlightRepository;
import com.a2.flightservice.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private PlaneRepository planeRepository;

    private ObjectMapper objectMapper;

    private JmsTemplate jmsTemplate;
    private String destinationCancelTicket;

    public FlightServiceImpl(FlightRepository flightRepository,
                             PlaneRepository planeRepository,
                             ObjectMapper objectMapper,
                             JmsTemplate jmsTemplate,
                             @Value("${destination.cancel-tickets}") String destinationCancelTicket) {
        this.flightRepository = flightRepository;
        this.planeRepository = planeRepository;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.destinationCancelTicket = destinationCancelTicket;
    }

    @Override
    public FlightCapacityDto findFlightCapacity(Long flightId) {
        Flight flight = flightRepository.findByFlightId(flightId)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Flight with id: %d not found.", flightId)));
        return new FlightCapacityDto(flight.getPlane().getCapacity());
    }

    @Override
    public FlightDto addFlight(FlightCreateDto flightCreateDto) {
        return null;
    }

    @Override
    public Page<FlightDto> findAllAvailableFlights() {
        return null;
    }

    @Override
    public Page<FlightDto> searchFlights() {
        return null;
    }

    @Override
    public void cancelFlight(Long flightId) {
        Flight flight = flightRepository.findByFlightId(flightId)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Flight with id: %d not found.", flightId)));
        FlightCancelDto flightCancelDto = new FlightCancelDto(flight.getFlightId(), flight.getMiles());
        try {
            jmsTemplate.convertAndSend(destinationCancelTicket, objectMapper.writeValueAsString(
                    new FlightCancelDto(flight.getFlightId(), flight.getMiles())));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        flightRepository.deleteByFlightId(flightId);
    }
}
