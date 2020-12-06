package com.alten.vehicle.repository;

import com.alten.vehicle.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findAllByCustomerId(Long customerId);

    void deleteByVehicleId(String id);


    @Modifying
    @Query("UPDATE Vehicle d SET d.status = 'OFFLINE' WHERE d.status = 'ONLINE' and d.lastPingDate < ?1")
    int updateVehicleStatus(ZonedDateTime statusCheckTime);
}
