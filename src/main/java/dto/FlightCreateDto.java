package dto;

import model.Airplane;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;

public class FlightCreateDto {

    private String beginDestination;
    private String finalDestination;
    private Long miles;
    private BigDecimal price;

    public FlightCreateDto(String beginDestination, String finalDestination, Long miles, BigDecimal price) {
        this.beginDestination = beginDestination;
        this.finalDestination = finalDestination;
        this.miles = miles;
        this.price = price;
    }

    public FlightCreateDto() {
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
