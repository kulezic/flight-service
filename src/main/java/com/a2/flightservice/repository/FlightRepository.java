package com.a2.flightservice.repository;


import com.a2.flightservice.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Optional<Flight> findByFlightId (Long flightId);
    void deleteByFlightId(Long flightId);

}
