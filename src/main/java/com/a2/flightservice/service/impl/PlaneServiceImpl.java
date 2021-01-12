package com.a2.flightservice.service.impl;

import com.a2.flightservice.domain.Plane;
import com.a2.flightservice.dto.PlaneCreateDto;
import com.a2.flightservice.dto.PlaneDto;
import com.a2.flightservice.exception.NotFoundException;
import com.a2.flightservice.mapper.PlaneMapper;
import com.a2.flightservice.repository.FlightRepository;
import com.a2.flightservice.repository.PlaneRepository;
import com.a2.flightservice.service.PlaneService;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final FlightRepository flightRepository;

    private final PlaneMapper planeMapper;

    public PlaneServiceImpl(PlaneRepository planeRepository,
                            FlightRepository flightRepository,
                            PlaneMapper planeMapper) {
        this.planeRepository = planeRepository;
        this.flightRepository = flightRepository;
        this.planeMapper = planeMapper;
    }


    //TODO @Valid
    @Override
    public PlaneDto addPlane(@Valid PlaneCreateDto planeCreateDto) {
        Plane plane = new Plane();
        plane.setCapacity(planeCreateDto.getCapacity());
        Plane rtn = planeRepository.save(plane);
        return planeMapper.planeToPlaneDto(rtn);
    }

    @Override
    public void deletePlaneById(Long planeId) {
        Plane plane = planeRepository.findByPlaneId(planeId).
                orElseThrow(() -> new NotFoundException(String.format("Plane with id: %d not found.", planeId)));
        if (flightRepository.findAllByPlane(plane).isEmpty()){
            planeRepository.deleteByPlaneId(planeId);
        }
    }

    @Override
    public List<PlaneDto> findAll() {
        return planeRepository.findAll().stream().map(planeMapper::planeToPlaneDto).collect(Collectors.toList());
    }


}
