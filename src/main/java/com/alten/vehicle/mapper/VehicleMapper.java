package com.alten.vehicle.mapper;


import com.alten.vehicle.dto.VehicleDto;
import com.alten.vehicle.dto.VehicleRequestDto;
import com.alten.vehicle.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class VehicleMapper {

    public static final VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

    public abstract Vehicle fromVehicleDto(VehicleDto vehicleDto);

    public abstract Vehicle fromVehicleRequestDto(VehicleRequestDto vehicleDto);

    public abstract VehicleDto toVehicleDto(Vehicle vehicle);

    public abstract List<VehicleDto> toVehicleDtoList(List<Vehicle> vehicles);


}
