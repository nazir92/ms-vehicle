package com.alten.vehicle.service.impl;

import com.alten.vehicle.exception.NoVehicleFoundException;
import com.alten.vehicle.model.Status;
import com.alten.vehicle.model.Vehicle;
import com.alten.vehicle.repository.VehicleRepository;
import com.alten.vehicle.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        LOGGER.info("Get all vehicles request received!");
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getCustomerAllVehicles(Long customerId) {
        LOGGER.info("Get customer all vehicles request received! CustomerId: {}", customerId);
        return vehicleRepository.findAllByCustomerId(customerId);
    }

    @Override
    public Vehicle getVehicle(String vehicleId) {
        LOGGER.info("Get vehicle request received! VehicleId: {}", vehicleId);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (optionalVehicle.isPresent()) {
            return optionalVehicle.get();
        } else {
            LOGGER.info("Couldn't find vehicle! VehicleId: {}", vehicleId);
            throw new NoVehicleFoundException("Couldn't find vehicle! VehicleId: " + vehicleId);
        }

    }

    @Override
    public Vehicle updateVehicle(String vehicleId, Vehicle vehicle) {
        LOGGER.info("Update vehicle request received! VehicleId: {} {}", vehicleId, vehicle);

        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (!optionalVehicle.isPresent()) {
            LOGGER.info("Couldn't find vehicle for update! VehicleId: {}", vehicleId);
            throw new NoVehicleFoundException("Couldn't find vehicle for update! VehicleId: " + vehicleId);
        }

        Vehicle legacyVehicle = optionalVehicle.get();
        legacyVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
        legacyVehicle.setCustomerId(vehicle.getCustomerId());
        legacyVehicle.setStatus(vehicle.getStatus());
        legacyVehicle.setVehicleId(vehicle.getVehicleId());

        Vehicle updatedVehicle = vehicleRepository.save(legacyVehicle);

        LOGGER.info("Vehicle updated successfully! {}", updatedVehicle);

        return updatedVehicle;
    }

    @Override
    @Transactional
    public void deleteVehicle(String vehicleId) {
        LOGGER.info("Delete vehicle request received! VehicleId: ", vehicleId);
        vehicleRepository.deleteByVehicleId(vehicleId);

    }

    @Override
    public Vehicle addVehicle(Long customerId, Vehicle vehicle) {
        LOGGER.info("Add new Vehicle request received! {}", vehicle);
        vehicle.setCustomerId(customerId);

        return vehicleRepository.save(vehicle);
    }

    @Override
    public void ping(String vehicleId) {
        LOGGER.info("Ping request received from Vehicle! VehicleId: {}", vehicleId);
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(vehicleId);
        if (!optionalVehicle.isPresent()) {
            LOGGER.info("Ping received from incorrect vehicle! VehicleId: {}", vehicleId);
            throw new NoVehicleFoundException("Ping received from incorrect vehicle! VehicleId:  " + vehicleId);
        }
        Vehicle vehicle = optionalVehicle.get();
        vehicle.setStatus(Status.ONLINE);
        vehicle.setLastPingDate(ZonedDateTime.now());
        vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void startUpdateVehiclesStatus() {
        LOGGER.info("Vehicles status update job started!");
        ZonedDateTime time = ZonedDateTime.now().minusSeconds(60);
        vehicleRepository.updateVehicleStatus(time);
    }


}
