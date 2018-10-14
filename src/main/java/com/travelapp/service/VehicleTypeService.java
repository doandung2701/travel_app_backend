package com.travelapp.service;

import com.travelapp.model.VehicleType;

import java.util.List;
import java.util.Optional;


public interface VehicleTypeService {


    VehicleType save(VehicleType vehicleTypeDTO);

    List<VehicleType> findAll();


    Optional<VehicleType> findOne(Long id);


    void delete(Long id);
}
