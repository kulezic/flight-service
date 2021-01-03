package com.a2.flightservice.service.impl;


import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.service.FlightService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService {

    @Override
    public FlightDto addFlight(FlightCreateDto flightCreateDto) {
        return null;
    }

    @Override
    public void cancelFlight(Long flightId) {

    }

    @Override
    public Page<FlightDto> findAllAvailableFlights() {
        return null;
    }

    @Override
    public Page<FlightDto> searchFlights() {
        return null;
    }
}