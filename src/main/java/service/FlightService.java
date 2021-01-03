package service;

public interface FlightService {

    Integer getFlightCapacityByFlightId(Long flightId);

    void cancelTicket(Long flightId);
}
