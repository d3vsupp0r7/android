
package com.example.oraritreni.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Soluzioni {

    @SerializedName("durata")
    @Expose
    private String durata;
    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles = null;

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

}
