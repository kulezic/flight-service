package com.a2.flightservice.service.implementation;

import com.a2.flightservice.dto.AirplaneCreateDto;
import com.a2.flightservice.dto.AirplaneDto;
import com.a2.flightservice.mapper.AirplaneMapper;
import com.a2.flightservice.model.Airplane;
import com.a2.flightservice.repository.AirplaneRepository;
import com.a2.flightservice.service.AirplaneService;
import org.springframework.stereotype.Service;

@Service
public class AirplaneServiceImpl implements AirplaneService {


    AirplaneRepository airplaneRepository;
    AirplaneMapper airplaneMapper;

    public AirplaneServiceImpl(AirplaneRepository airplaneRepository, AirplaneMapper airplaneMapper) {
        this.airplaneRepository = airplaneRepository;
        this.airplaneMapper = airplaneMapper;
    }


    @Override
    public void deleteById(Long id) {
        airplaneRepository.deleteById(id);
    }

    @Override
    public AirplaneDto add(AirplaneCreateDto airplaneCreateDto) {
        Airplane airplane = airplaneMapper.airplaneCreateDtoToAirplane(airplaneCreateDto);
        airplaneRepository.save(airplane);
        return airplaneMapper.airplaneToAirplaneDto(airplane);
    }
}
