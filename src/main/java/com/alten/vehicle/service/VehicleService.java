package com.alten.vehicle.service;

import com.alten.vehicle.model.Vehicle;

import java.util.List;

public interface VehicleService {


    List<Vehicle> getAllVehicles();

    List<Vehicle> getCustomerAllVehicles(Long customerId);

    Vehicle getVehicle(String vehicleId);

    Vehicle updateVehicle(String vehicleId, Vehicle vehicle);

    void deleteVehicle(String vehicleId);

    Vehicle addVehicle(Long customerId, Vehicle vehicle);

    void ping(String vehicleId);

    void startUpdateVehiclesStatus();

}
