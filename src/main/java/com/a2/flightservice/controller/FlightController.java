package com.a2.flightservice.controller;

import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.security.CheckSecurity;
import com.a2.flightservice.service.FlightService;
import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;


    @GetMapping
    public ResponseEntity<Page<FlightDto>> findAllAvailableFlights(Pageable pageable){
        return new ResponseEntity<>(flightService.findAllAvailableFlights(pageable), HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<FlightDto>> findAllAvailableFlights(){
        return new ResponseEntity<>(flightService.findAllAvailableFlightsList(), HttpStatus.OK);
    }
//    @GetMapping
//    public ResponseEntity<Long> countOfFlights(){
//        return new ResponseEntity<>(flightService.getCountOfFlights(),HttpStatus.OK);
//    }

    @GetMapping("/search")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Page<FlightDto>> searchFlights(@RequestHeader("Authorization") String authorization,
                                                          Pageable pageable,
                                                         @SearchSpec Specification<FlightDto> specs){
        return new ResponseEntity<>(flightService.searchFlights(specs, pageable), HttpStatus.OK);
    }

    @PostMapping("/add")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<FlightDto> addFlight(@RequestHeader("Authorization") String authorization,
                                               @RequestBody FlightCreateDto flightCreateDto){
        return new ResponseEntity<>(flightService.addFlight(flightCreateDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Void> cancelFlight(@RequestHeader("Authorization") String authorization,
                                             @PathVariable("id") Long id) {
        flightService.cancelFlight(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(
                                             @PathVariable("id") Long id) {
        FlightDto flightDto = flightService.findById(id);
        return new ResponseEntity<>(flightDto,HttpStatus.OK);
    }

}
