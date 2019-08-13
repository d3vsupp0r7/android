package org.sglba.trainman.ui.model;

import org.sglba.trainman.model.Soluzioni;
import org.sglba.trainman.model.Vehicle;

import java.io.Serializable;
import java.util.List;

public class TrainSolution implements Serializable {

    private Soluzioni solution;
    private Vehicle firstVehicle;
    private Vehicle lastVehicle;
    private List<Vehicle> VehicleForSolution;

    public TrainSolution() {
    }

    public TrainSolution(Soluzioni solution, Vehicle firstVehicle, Vehicle lastVehicle, List<Vehicle> vehicleForSolution) {
        this.solution = solution;
        this.firstVehicle = firstVehicle;
        this.lastVehicle = lastVehicle;
        VehicleForSolution = vehicleForSolution;
    }

    public Soluzioni getSolution() {
        return solution;
    }

    public void setSolution(Soluzioni solution) {
        this.solution = solution;
    }

    public Vehicle getFirstVehicle() {
        return firstVehicle;
    }

    public void setFirstVehicle(Vehicle firstVehicle) {
        this.firstVehicle = firstVehicle;
    }

    public Vehicle getLastVehicle() {
        return lastVehicle;
    }

    public void setLastVehicle(Vehicle lastVehicle) {
        this.lastVehicle = lastVehicle;
    }

    public List<Vehicle> getVehicleForSolution() {
        return VehicleForSolution;
    }

    public void setVehicleForSolution(List<Vehicle> vehicleForSolution) {
        VehicleForSolution = vehicleForSolution;
    }

    @Override
    public String toString() {
        return "TrainSolution{" +
                "solution=" + solution +
                ", firstVehicle=" + firstVehicle +
                ", lastVehicle=" + lastVehicle +
                ", VehicleForSolution=" + VehicleForSolution +
                '}';
    }
}
