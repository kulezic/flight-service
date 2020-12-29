package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightDto {


    private Integer idFlight;
    private String beginDestination;
    private String finalDestination;
    private String duration;
    private int price;

    @JsonProperty("airplane")
    private AirplaneDto airplaneDto;

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

    public AirplaneDto getAirplaneDto() {
        return airplaneDto;
    }

    public void setAirplaneDto(AirplaneDto airplaneDto) {
        this.airplaneDto = airplaneDto;
    }

    public Integer getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Integer idFlight) {
        this.idFlight = idFlight;
    }
}
