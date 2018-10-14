package com.travelapp.controller;
import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Vehicle;
import com.travelapp.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Vehicle.
 */
@RestController
@RequestMapping("/api")
public class VehicleResource {

    private final Logger log = LoggerFactory.getLogger(VehicleResource.class);

    private static final String ENTITY_NAME = "vehicle";

    private final VehicleService vehicleService;
    @Autowired
    public VehicleResource(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }


    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicle(@Valid @RequestBody Vehicle vehicleDTO) throws URISyntaxException {
        log.debug("REST request to save Vehicle : {}", vehicleDTO);
        if (vehicleDTO.getId() != null) {
            throw new BadRequestException("A new vehicle cannot already have an ID");
        }
        Vehicle result = vehicleService.save(vehicleDTO);
        return ResponseEntity.created(new URI("/api/vehicles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/vehicles")
    public ResponseEntity<Vehicle> updateVehicle(@Valid @RequestBody Vehicle vehicleDTO) throws URISyntaxException {
        log.debug("REST request to update Vehicle : {}", vehicleDTO);
        if (vehicleDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Vehicle result = vehicleService.save(vehicleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehicleDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        log.debug("REST request to get all Vehicles");
        return vehicleService.findAll();
    }
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        log.debug("REST request to get Vehicle : {}", id);
        Optional<Vehicle> vehicleDTO = vehicleService.findOne(id);
        if (vehicleDTO==null)
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        return ResponseEntity.ok(vehicleDTO.get());
    }


    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        log.debug("REST request to delete Vehicle : {}", id);
        vehicleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
