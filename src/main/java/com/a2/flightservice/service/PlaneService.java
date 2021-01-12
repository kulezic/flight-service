package com.a2.flightservice.service;

import com.a2.flightservice.dto.PlaneCreateDto;
import com.a2.flightservice.dto.PlaneDto;

import java.util.List;

public interface PlaneService {

    PlaneDto addPlane(PlaneCreateDto planeCreateDto);

    void deletePlaneById(Long planeId);

    List<PlaneDto> findAll();
}
