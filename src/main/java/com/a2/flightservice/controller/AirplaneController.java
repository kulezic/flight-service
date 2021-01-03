package com.a2.flightservice.controller;

import com.a2.flightservice.dto.PlaneCreateDto;
import com.a2.flightservice.dto.PlaneDto;
import com.a2.flightservice.service.PlaneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.a2.flightservice.security.CheckSecurity;

import javax.validation.Valid;

@RestController
@RequestMapping("/airplane")
public class AirplaneController {

    private PlaneService planeService;

    public AirplaneController(PlaneService planeService){
        this.planeService = planeService;
    }


    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<PlaneDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid PlaneCreateDto planeCreateDto) {
        return new ResponseEntity<>(planeService.addPlane(planeCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        planeService.deletePlaneById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
