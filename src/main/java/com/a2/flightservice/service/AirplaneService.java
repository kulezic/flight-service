package com.a2.flightservice.service;

import com.a2.flightservice.dto.AirplaneCreateDto;
import com.a2.flightservice.dto.AirplaneDto;

public interface AirplaneService {

    void deleteById(Long id);

    AirplaneDto add(AirplaneCreateDto airplaneCreateDto);
}
