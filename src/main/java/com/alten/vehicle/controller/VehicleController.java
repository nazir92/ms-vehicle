package com.alten.vehicle.controller;


import com.alten.vehicle.dto.VehicleDto;
import com.alten.vehicle.dto.VehicleRequestDto;
import com.alten.vehicle.mapper.VehicleMapper;
import com.alten.vehicle.model.Vehicle;
import com.alten.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class VehicleController {


    private VehicleService vehicleService;


    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        return new ResponseEntity<>(VehicleMapper.INSTANCE.toVehicleDtoList(vehicleService.getAllVehicles()), HttpStatus.OK);
    }

    @GetMapping("/customers/{customer-id}/vehicles")
    public ResponseEntity<List<VehicleDto>> getClientAllVehicles(@PathVariable("customer-id") Long customerId) {
        return new ResponseEntity<>(VehicleMapper.INSTANCE.toVehicleDtoList(vehicleService.getCustomerAllVehicles(customerId)), HttpStatus.OK);
    }

    @GetMapping("/vehicles/{vehicle-id}")
    public ResponseEntity<VehicleDto> getVehicle(@PathVariable("vehicle-id") String vehicleId) {
        return new ResponseEntity<>(VehicleMapper.INSTANCE.toVehicleDto(vehicleService.getVehicle(vehicleId)), HttpStatus.OK);
    }


    @PutMapping("/vehicles/{vehicle-id}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable("vehicle-id") String vehicleId,
                                                    @RequestBody VehicleDto vehicleDto) {
        Vehicle vehicle = VehicleMapper.INSTANCE.fromVehicleDto(vehicleDto);
        return new ResponseEntity<>(VehicleMapper.INSTANCE.toVehicleDto(vehicleService.updateVehicle(vehicleId, vehicle)), HttpStatus.OK);
    }


    @DeleteMapping("/vehicles/{vehicle-id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable("vehicle-id") String vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/customers/{customer-id}/vehicles")
    public ResponseEntity<VehicleDto> addVehicle(@PathVariable("customer-id") Long customerId,
                                                 @RequestBody VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle = VehicleMapper.INSTANCE.fromVehicleRequestDto(vehicleRequestDto);

        return new ResponseEntity<>(VehicleMapper.INSTANCE.toVehicleDto(vehicleService.addVehicle(customerId, vehicle)), HttpStatus.CREATED);
    }

    @PatchMapping("/vehicles/ping/{vehicle-id}")
    public ResponseEntity<Void> ping(@PathVariable("vehicle-id") String vehicleId) {
        vehicleService.ping(vehicleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
