package com.a2.flightservice.service;

import com.a2.flightservice.domain.Flight;
import com.a2.flightservice.dto.FlightCapacityDto;
import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import org.springframework.data.domain.Page;

public interface FlightService {

    FlightCapacityDto findFlightCapacity(Long flightId);

    FlightDto addFlight(FlightCreateDto flightCreateDto);

    Page<FlightDto> findAllAvailableFlights();

    Page<FlightDto> searchFlights();

    void cancelFlight(Long flightId);
}
