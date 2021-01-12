package com.a2.flightservice.dto;


import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class FlightCreateDto {

    private String startDestination;
    private String endDestination;
    private Long miles;
    private BigDecimal price;
    private Long planeId;

    public FlightCreateDto() {
    }

    public FlightCreateDto(String startDestination,
                           String endDestination,
                           Long miles,
                           BigDecimal price,
                           Long planeId) {
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.miles = miles;
        this.price = price;
        this.planeId = planeId;
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

    public Long getPlaneId() {
        return planeId;
    }

    public void setPlaneId(Long planeId) {
        this.planeId = planeId;
    }
}
