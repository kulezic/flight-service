package com.a2.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FlightDto {

    private Long flightId;
    private String startDestination;
    private String endDestination;
    private Long miles;
    private BigDecimal price;

    public FlightDto() {
    }

    public FlightDto(Long flightId, String startDestination, String endDestination, Long miles, BigDecimal price) {
        this.flightId = flightId;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.miles = miles;
        this.price = price;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public Long getMiles() {
        return miles;
    }

    public void setMiles(Long miles) {
        this.miles = miles;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
