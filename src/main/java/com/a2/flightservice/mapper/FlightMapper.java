package com.a2.flightservice.mapper;

import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.exception.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {

    /*
    private AirplaneRepository airplaneRepository;
    private AirplaneMapper airplaneMapper;


    public FlightMapper(AirplaneRepository airplaneRepository, AirplaneMapper airplaneMapper) {
        this.airplaneRepository = airplaneRepository;
        this.airplaneMapper = airplaneMapper;
    }

    public Flight flightCreateDtoToFlight(FlightCreateDto flightCreateDto){
        Flight flight = new Flight();
        flight.setBeginDestination(flightCreateDto.getBeginDestination());
        flight.setFinalDestination(flightCreateDto.getFinalDestination());
        flight.setDuration(flightCreateDto.getDuration());
        flight.setPrice(flightCreateDto.getPrice());
        flight.setAirplane(airplaneRepository.findById(flightCreateDto.getIdAirplane())
                .orElseThrow(()->
                        new NotFoundException(String.format("Airplane with id: %d does not exists.",flightCreateDto.getIdAirplane()))));

        return flight;
    }

    public FlightDto flightToFlightDto(Flight flight){
        FlightDto flightDto = new FlightDto();
        flightDto.setIdFlight(flight.getIdFlight());
        flightDto.setBeginDestination(flight.getBeginDestination());
        flightDto.setFinalDestination(flight.getFinalDestination());
        flightDto.setDuration(flight.getDuration());
        flightDto.setPrice(flight.getPrice());
        flightDto.setAirplaneDto(airplaneMapper.airplaneToAirplaneDto(flight.getAirplane()));
        return flightDto;
    }

     */
}
