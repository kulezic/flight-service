package com.a2.flightservice.service;

import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.dto.FlightCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {

    FlightDto addFlight(FlightCreateDto flightCreateDto);

    void cancelFlight(Long flightId);

    Page<FlightDto> findAllAvailableFlights();

    Page<FlightDto> searchFlights();
}
