package com.a2.flightservice.mapper;

import com.a2.flightservice.domain.Flight;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightMapper {
    private PlaneMapper planeMapper;

    public FlightMapper(PlaneMapper planeMapper) {
        this.planeMapper = planeMapper;
    }

    public FlightDto flightToFlightDto(Flight flight){
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightId(flight.getFlightId());
        flightDto.setStartDestination(flight.getStartDestination());
        flightDto.setEndDestination(flight.getEndDestination());
        flightDto.setMiles(flight.getMiles());
        flightDto.setPrice(flight.getPrice());
        flightDto.setPlaneDto(planeMapper.planeToPlaneDto(flight.getPlane()));
        flightDto.setFlightStatus(flight.getFlightStatus());
        return flightDto;
    }

    public List<FlightDto> flightsToFlightsDto(List<Flight> flights) {
        List<FlightDto> flightDtos = new ArrayList<>();
        for(Flight flight: flights){
            flightDtos.add(flightToFlightDto(flight));
        }
        return flightDtos;
    }

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
        flight.setPlane(airplaneRepository.findById(flightCreateDto.getIdAirplane())
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
        flightDto.setAirplaneDto(airplaneMapper.airplaneToAirplaneDto(flight.getPlane()));
        return flightDto;
    }

     */
}
