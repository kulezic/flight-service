package com.a2.flightservice.repository;

import com.a2.flightservice.domain.Flight;
import com.a2.flightservice.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Override
    Optional<Flight> findById(Long flightId);

    Optional<List<Flight>> findAllByPlane(Plane plane);

    void deleteByFlightId(Long flightId);
}
