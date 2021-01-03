package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class FlightDto {


    private Long flightId;
    private String beginDestination;
    private String finalDestination;
    private Long miles;
    private BigDecimal price;

    public FlightDto(Long flightId, String beginDestination, String finalDestination, Long miles, BigDecimal price) {
        this.flightId = flightId;
        this.beginDestination = beginDestination;
        this.finalDestination = finalDestination;
        this.miles = miles;
        this.price = price;
    }

    public FlightDto() {
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getBeginDestination() {
        return beginDestination;
    }

    public void setBeginDestination(String beginDestination) {
        this.beginDestination = beginDestination;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
        this.finalDestination = finalDestination;
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
