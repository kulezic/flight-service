package com.a2.flightservice.service.implementation;

import com.a2.flightservice.dto.PlaneCreateDto;
import com.a2.flightservice.dto.PlaneDto;
import com.a2.flightservice.mapper.PlaneMapper;
import com.a2.flightservice.domain.Plane;
import com.a2.flightservice.repository.PlaneRepository;
import com.a2.flightservice.service.PlaneService;

public class PlaneServiceImpl implements PlaneService {


    PlaneRepository planeRepository;
    PlaneMapper planeMapper;

    public PlaneServiceImpl(PlaneRepository planeRepository, PlaneMapper planeMapper) {
        this.planeRepository = planeRepository;
        this.planeMapper = planeMapper;
    }


    @Override
    public PlaneDto addPlane(PlaneCreateDto planeCreateDto) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


}
