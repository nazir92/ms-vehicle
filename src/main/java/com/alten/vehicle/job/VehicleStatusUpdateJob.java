package com.alten.vehicle.job;

import com.alten.vehicle.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VehicleStatusUpdateJob {

    private VehicleService vehicleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleStatusUpdateJob.class);

    @Autowired
    public VehicleStatusUpdateJob(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Scheduled(fixedDelay = 60000)
    public void startJob() {
        LOGGER.info("Vehicle status update job started!");
        vehicleService.startUpdateVehiclesStatus();

    }
}
