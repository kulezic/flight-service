package com.a2.flightservice.service;

import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {

    Integer getFlightCapacityByFlightId(Long flightId);


    List<FlightDto> findAll();

    void deleteById(Long id);

    FlightDto add(FlightCreateDto flightCreateDto);

    FlightDto findById(Long id);
}
