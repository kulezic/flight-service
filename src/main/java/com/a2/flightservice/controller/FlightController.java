package com.a2.flightservice.controller;

import com.a2.flightservice.dto.FlightCapacityDto;
import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.security.CheckSecurity;
import com.a2.flightservice.service.FlightService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;


    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})

    @GetMapping
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Page<FlightDto>> findAllAvailableFlights(@RequestHeader("Authorization") String authorization,
                                                                   @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(flightService.findAllAvailableFlights(pageable), HttpStatus.OK);
    }

    @GetMapping("/search/")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Page<FlightDto>> searchFlights(@RequestHeader("Authorization") String authorization,
                                                         @ApiIgnore Pageable pageable){
        return new ResponseEntity<>(flightService.searchFlights(), HttpStatus.OK);
    }

    @PostMapping("/add/")
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

    @GetMapping("/capacity/{id}")
    public ResponseEntity<FlightCapacityDto> findFlightCapacity(@PathVariable("id") Long id){
        return new ResponseEntity<>(flightService.findFlightCapacity(id), HttpStatus.OK);
    }
}
