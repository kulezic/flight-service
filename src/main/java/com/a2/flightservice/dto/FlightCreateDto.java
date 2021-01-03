package dto;

import model.Airplane;

import javax.persistence.ManyToOne;

public class FlightCreateDto {

    private String beginDestination;
    private String finalDestination;
    private String duration;
    private int price;
    private Integer idAirplane;

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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(Integer idAirplane) {
        this.idAirplane = idAirplane;
    }
}
