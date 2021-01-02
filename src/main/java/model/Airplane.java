package model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAirplane;

    private String name;
    private int capacity;

    @OneToMany(mappedBy = "airplane")
    private List<Flight> flights;

    public Airplane(){}

    public Airplane(String name, int capacity){
        this.name = name;
        this.capacity = capacity;
    }

    public int getIdAirplane() {
        return idAirplane;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

   /* public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight){
        if(!flights.contains(flight))
            flights.add(flight);
    }*/
}

