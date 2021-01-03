package com.a2.flightservice.controller;

import com.a2.flightservice.dto.FlightCreateDto;
import com.a2.flightservice.dto.FlightDto;
import com.a2.flightservice.model.Flight;
import com.a2.flightservice.security.CheckSecurity;
import com.a2.flightservice.service.FlightService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("flight/")
public class FlightController {

    private FlightService flightService;

    @ApiOperation(value = "Get all flights")
    @GetMapping
    @CheckSecurity (roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<FlightDto>> findAll(@RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>(flightService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<FlightDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid FlightCreateDto flightCreateDto) {
        return new ResponseEntity<>(flightService.add(flightCreateDto), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<FlightDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(flightService.findById(id), HttpStatus.OK);
    }

}
