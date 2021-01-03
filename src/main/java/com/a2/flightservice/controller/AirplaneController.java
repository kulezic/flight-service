package com.a2.flightservice.controller;

import com.a2.flightservice.dto.AirplaneCreateDto;
import com.a2.flightservice.dto.AirplaneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.a2.flightservice.security.CheckSecurity;
import com.a2.flightservice.service.AirplaneService;

import javax.validation.Valid;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    private AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService){ this.airplaneService = airplaneService;}


    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<AirplaneDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid AirplaneCreateDto airplaneCreateDto) {
        return new ResponseEntity<>(airplaneService.add(airplaneCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        airplaneService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
