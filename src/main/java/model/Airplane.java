package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airplane {


    private String idAirplane;

    public void setIdAirplane(String idAirplane) {
        this.idAirplane = idAirplane;
    }

    @Id
    public String getIdAirplane() {
        return idAirplane;
    }
}
