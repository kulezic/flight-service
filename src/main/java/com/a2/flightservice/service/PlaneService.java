package com.a2.flightservice.service;

import com.a2.flightservice.dto.PlaneCreateDto;
import com.a2.flightservice.dto.PlaneDto;

public interface PlaneService {

    PlaneDto addPlane(PlaneCreateDto planeCreateDto);

    void deletePlaneById(Long planeId);
}
