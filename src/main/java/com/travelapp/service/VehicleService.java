package com.travelapp.service;


import com.travelapp.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {


    Vehicle save(Vehicle vehicleDTO);

    List<Vehicle> findAll();


    Optional<Vehicle> findOne(Long id);


    void delete(Long id);
}
