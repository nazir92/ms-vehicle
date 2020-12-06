package com.alten.vehicle.service;


import com.alten.vehicle.exception.NoVehicleFoundException;
import com.alten.vehicle.model.Status;
import com.alten.vehicle.model.Vehicle;
import com.alten.vehicle.repository.VehicleRepository;
import com.alten.vehicle.service.impl.VehicleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleServiceTest {

    @InjectMocks
    VehicleServiceImpl vehicleService;

    @Mock
    VehicleRepository vehicleRepository;


    @Test
    public void getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicles.add(vehicle);
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);
        assertEquals(vehicles.size(), vehicleService.getAllVehicles().size());
    }

    @Test
    public void getCustomerAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle = new Vehicle();
        vehicles.add(vehicle);
        when(vehicleService.getCustomerAllVehicles(12l)).thenReturn(vehicles);
        assertEquals(vehicles.size(), vehicleService.getCustomerAllVehicles(12l).size());
    }


    @Test
    public void getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setCustomerId(12l);
        vehicle.setVehicleId("test");
        vehicle.setStatus(Status.ONLINE);
        Optional<Vehicle> optional = Optional.of(vehicle);
        when(vehicleRepository.findById("test")).thenReturn(optional);
        assertEquals(vehicleService.getVehicle("test").getVehicleId(), vehicle.getVehicleId());
    }


    @Test(expected = NoVehicleFoundException.class)
    public void getVehicleException() {
        vehicleService.getVehicle("test");
    }

    @Test
    public void updateVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setCustomerId(12l);
        vehicle.setVehicleId("test");
        vehicle.setStatus(Status.ONLINE);
        Optional<Vehicle> optional = Optional.of(vehicle);
        when(vehicleRepository.findById("test")).thenReturn(optional);
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setVehicleId("test");
        updatedVehicle.setRegistrationNumber("newTest");
        when(vehicleRepository.save(vehicle)).thenReturn(updatedVehicle);
        assertEquals(updatedVehicle.getRegistrationNumber(), vehicleService.updateVehicle("test", updatedVehicle).getRegistrationNumber());

    }

    @Test(expected = NoVehicleFoundException.class)
    public void updateVehicleException() {
        vehicleService.updateVehicle("test", new Vehicle());
    }


    @Test(expected = Test.None.class)
    public void deleteVehicle() {
        vehicleService.deleteVehicle("test");
    }


    @Test
    public void addVehicle() {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId("test");
        vehicle.setRegistrationNumber("testReg");
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        Vehicle newVehicle = vehicleService.addVehicle(12l, vehicle);
        assertEquals(vehicle.getRegistrationNumber(), newVehicle.getRegistrationNumber());
    }

    @Test
    public void ping() {
        Vehicle vehicle = new Vehicle();
        vehicle.setCustomerId(12l);
        vehicle.setVehicleId("test");
        vehicle.setStatus(Status.ONLINE);
        Optional<Vehicle> optional = Optional.of(vehicle);
        when(vehicleRepository.findById("test")).thenReturn(optional);
        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);
        vehicleService.ping("test");
    }


    @Test(expected = NoVehicleFoundException.class)
    public void pingException() {
        vehicleService.ping("test");
    }

    @Test
    public void startUpdateVehiclesStatus() {
        vehicleService.startUpdateVehiclesStatus();
    }

}
