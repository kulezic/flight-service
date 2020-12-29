package model;

import javax.persistence.*;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFlight;

    private String beginDestination;
    private String finalDestination;
    private String duration;
    //ili bolje string
    private int price;

    @ManyToOne
    private Airplane airplane;

    public Flight() {
    }

    public Flight(int idFlight, String beginDestination, String finalDestination, String duration, int price, Airplane airplane) {
        this.idFlight = idFlight;
        this.beginDestination = beginDestination;
        this.finalDestination = finalDestination;
        this.duration = duration;
        this.price = price;
        this.airplane = airplane;
    }

    public int getIdFlight() {
        return idFlight;
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

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}
