package com.a2.flightservice.repository;

import com.a2.flightservice.model.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane,Long> {

}
