package com.a2.flightservice.mapper;

import com.a2.flightservice.dto.PlaneDto;
import com.a2.flightservice.domain.Plane;
import com.a2.flightservice.dto.PlaneCreateDto;
import org.springframework.stereotype.Component;

@Component
public class PlaneMapper {

    public PlaneDto planeToPlaneDto(Plane plane){
        PlaneDto planeDto = new PlaneDto();
        planeDto.setPlaneId(plane.getPlaneId());
        planeDto.setCapacity(plane.getCapacity());
        return planeDto;
    }
}
