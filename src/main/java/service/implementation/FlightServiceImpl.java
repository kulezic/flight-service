package service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CancelTicketDto;
import exception.NotFoundException;
import model.Flight;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import repository.AirplaneRepository;
import repository.FlightRepository;
import service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository;
    private AirplaneRepository airplaneRepository;

    private ObjectMapper objectMapper;

    private JmsTemplate jmsTemplate;
    private String destinationCancelTicket;

    public FlightServiceImpl(FlightRepository flightRepository,
                             AirplaneRepository airplaneRepository,
                             ObjectMapper objectMapper,
                             JmsTemplate jmsTemplate,
                             @Value("${destination.cancel-tickets}") String destinationCancelTicket) {
        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
        this.destinationCancelTicket = destinationCancelTicket;
    }

    @Override
    public Integer getFlightCapacityByFlightId(Long flightId) {
        Flight flight = flightRepository.findByIdFlight(flightId);
        return flight.getAirplane().getCapacity();
    }

    @Override
    public void cancelTicket(Long flightId) {
        Flight flight = flightRepository.findByIdFlight(flightId)
                .orElseThrow(() -> new NotFoundException(String
                .format("Flight with id: %d not found.", flightId)));
        CancelTicketDto cancelTicketDto = new CancelTicketDto(flight.getFlightId(), flight.getMiles());
        try {
            jmsTemplate.convertAndSend(destinationCancelTicket, objectMapper.writeValueAsString(
                    new CancelTicketDto(flight.getFlightId(), flight.getMiles())));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        flightRepository.deleteByIdFlight(flightId);
    }
}
