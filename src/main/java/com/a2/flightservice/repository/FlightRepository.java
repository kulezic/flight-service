package com.a2.flightservice.repository;


import com.a2.flightservice.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Flight findByIdFlight(Long flightId);

    void deleteByIdFlight(Long flightId);
}
