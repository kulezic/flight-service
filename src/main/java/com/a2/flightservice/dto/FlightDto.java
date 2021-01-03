package com.a2.flightservice.dto;

import java.math.BigDecimal;

public class FlightDto {

    private Long flightId;
    private String startDestination;
    private String endDestination;
    private Long miles;
    private BigDecimal price;

    private Integer capacity;

    public FlightDto() {
    }

    public FlightDto(Long flightId,
                     String startDestination,
                     String endDestination,
                     Long miles,
                     BigDecimal price,
                     Integer capacity) {
        this.flightId = flightId;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.miles = miles;
        this.price = price;
        this.capacity = capacity;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
