package org.lba.android.simple.trainer.activity.retrofit.model.station;

import java.util.List;

public class TrainSolution {

    private Soluzioni solution;

    private Vehicle firstVehicle;

    private Vehicle lastVehicle;

    private List<Vehicle> vehicleForSolution;

    public void setSolution(Soluzioni solution) {
        this.solution = solution;
    }

    public void setFirstVehicle(Vehicle firstVehicle) {
        this.firstVehicle = firstVehicle;
    }

    public void setLastVehicle(Vehicle lastVehicle) {
        this.lastVehicle = lastVehicle;
    }

    public void setVehicleForSolution(List<Vehicle> vehicleForSolution) {
        this.vehicleForSolution = vehicleForSolution;
    }

    public Soluzioni getSolution() {
        return solution;
    }

    public Vehicle getFirstVehicle() {
        return firstVehicle;
    }

    public Vehicle getLastVehicle() {
        return lastVehicle;
    }

    public List<Vehicle> getVehicleForSolution() {
        return vehicleForSolution;
    }

    public TrainSolution(Soluzioni solution, Vehicle firstVehicle, Vehicle lastVehicle, List<Vehicle> vehicleForSolution) {
        this.solution = solution;
        this.firstVehicle = firstVehicle;
        this.lastVehicle = lastVehicle;
        this.vehicleForSolution = vehicleForSolution;
    }
}
