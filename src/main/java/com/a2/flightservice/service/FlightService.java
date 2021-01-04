package com.a2.flightservice.service;

import com.a2.flightservice.domain.Flight;
import com.a2.flightservice.dto.FlightCapacityDto;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.dto.FlightCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface FlightService {

    FlightDto addFlight(FlightCreateDto flightCreateDto);

    void cancelFlight(Long flightId);

    Page<FlightDto> findAllAvailableFlights(Pageable pageable);

    Page<FlightDto> searchFlights();

    FlightCapacityDto findFlightCapacity(Long flightId);

    Page<FlightDto> findAll(Specification<FlightDto> where, Pageable pageable);
}
