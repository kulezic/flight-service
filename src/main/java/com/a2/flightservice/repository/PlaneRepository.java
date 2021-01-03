package com.a2.flightservice.repository;

import com.a2.flightservice.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane,Long> {
    Optional<Plane> findByPlaneId(Long planeId);
}
