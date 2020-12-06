package com.alten.vehicle.controller;


import com.alten.vehicle.dto.VehicleDto;
import com.alten.vehicle.dto.VehicleRequestDto;
import com.alten.vehicle.service.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class VehicleControllerTest {


    @InjectMocks
    VehicleController vehicleController;

    @Mock
    VehicleService vehicleService;

    @Test
    public void getAllVehicles() {
        assertEquals(HttpStatus.OK, vehicleController.getAllVehicles().getStatusCode());
    }


    @Test
    public void getClientAllVehicles() {
        assertEquals(HttpStatus.OK, vehicleController.getClientAllVehicles(12l).getStatusCode());
    }


    @Test
    public void getVehicle() {
        assertEquals(HttpStatus.OK, vehicleController.getVehicle("test").getStatusCode());
    }

    @Test
    public void updateVehicle() {

        VehicleDto vehicle = new VehicleDto();
        vehicle.setVehicleId("test");
        vehicle.setCustomerId(12l);
        assertEquals(HttpStatus.OK, vehicleController.updateVehicle("test", vehicle).getStatusCode());
    }

    @Test
    public void deleteVehicle() {
        assertEquals(HttpStatus.NO_CONTENT, vehicleController.deleteVehicle("test").getStatusCode());
    }

    @Test
    public void addVehicle() {
        assertEquals(HttpStatus.CREATED, vehicleController.addVehicle(12l, new VehicleRequestDto()).getStatusCode());
    }

    @Test
    public void ping(){
        assertEquals(HttpStatus.OK,vehicleController.ping("test").getStatusCode());
    }


}
