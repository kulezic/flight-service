package com.a2.flightservice.mapper;

import com.a2.flightservice.dto.AirplaneCreateDto;
import com.a2.flightservice.dto.AirplaneDto;
import com.a2.flightservice.model.Airplane;
import org.springframework.stereotype.Component;

@Component
public class AirplaneMapper {


    public AirplaneDto airplaneToAirplaneDto(Airplane airplane){
        AirplaneDto airplaneDto = new AirplaneDto();
        //airplaneDto.setIdAirplane(airplane.getIdAirplane());
        airplaneDto.setName(airplane.getName());
        airplaneDto.setCapacity(airplane.getCapacity());
        return airplaneDto;
    }

    public Airplane airplaneCreateDtoToAirplane(AirplaneCreateDto airplaneCreateDto){
        Airplane airplane = new Airplane();
        airplane.setName(airplaneCreateDto.getName());
        airplane.setCapacity(airplaneCreateDto.getCapacity());
        return airplane;
    }
}
