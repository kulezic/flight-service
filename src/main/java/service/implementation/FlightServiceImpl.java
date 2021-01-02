package service.implementation;

import model.Flight;
import org.springframework.stereotype.Service;
import repository.AirplaneRepository;
import repository.FlightRepository;
import service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private AirplaneRepository airplaneRepository;

    public FlightServiceImpl(FlightRepository flightRepository, AirplaneRepository airplaneRepository) {
        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
    }

    @Override
    public Integer getFlightCapacityByFlightId(Long flightId) {
        Flight flight = flightRepository.findByIdFlight(flightId);
        return flight.getAirplane().getCapacity();
    }
}
