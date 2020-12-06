package com.alten.vehicle.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @NotNull
    private String vehicleId;
    @NotNull
    private String registrationNumber;
    @NotNull
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.OFFLINE;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime lastPingDate = ZonedDateTime.now();


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }



    public void setLastPingDate(ZonedDateTime lastPingDate) {
        this.lastPingDate = lastPingDate;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", customerId=" + customerId +
                ", status=" + status +
                ", lastPingDate=" + lastPingDate +
                '}';
    }
}
