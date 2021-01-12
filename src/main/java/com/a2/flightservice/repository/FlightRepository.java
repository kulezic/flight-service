package com.a2.flightservice.repository;

import com.a2.flightservice.domain.Flight;
import com.a2.flightservice.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> , JpaSpecificationExecutor<Flight> {

    Optional<Flight> findByFlightId(Long flightId);

    Optional<List<Flight>> findAllByPlane(Plane plane);

    @Transactional
    void deleteByFlightId(Long flightId);
}
