package com.a2.flightservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    private String startDestination;
    private String endDestination;
    private Long miles;
    private BigDecimal price;
    private String flightStatus;

    @ManyToOne
    private Plane plane;

    public Flight() {
    }

    public Flight(Long flightId,
                  String startDestination,
                  String endDestination,
                  Long miles,
                  BigDecimal price,
                  Plane plane,
                  String flightStatus) {
        this.flightId = flightId;
        this.startDestination = startDestination;
        this.endDestination = endDestination;
        this.miles = miles;
        this.price = price;
        this.plane = plane;
        this.flightStatus = flightStatus;
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

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }
}
