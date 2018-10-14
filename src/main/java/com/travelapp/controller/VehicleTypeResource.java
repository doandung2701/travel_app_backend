package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Vehicle;
import com.travelapp.model.VehicleType;
import com.travelapp.service.VehicleTypeService;
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
 * REST controller for managing VehicleType.
 */
@RestController
@RequestMapping("/api")
public class VehicleTypeResource {

    private final Logger log = LoggerFactory.getLogger(VehicleTypeResource.class);

    private static final String ENTITY_NAME = "vehicleType";

    private final VehicleTypeService vehicleTypeService;
    @Autowired
    public VehicleTypeResource(VehicleTypeService vehicleTypeService) {
        this.vehicleTypeService = vehicleTypeService;
    }

    @PostMapping("/vehicle-types")
    public ResponseEntity<VehicleType> createVehicleType(@Valid @RequestBody VehicleType vehicleTypeDTO) throws URISyntaxException {
        log.debug("REST request to save VehicleType : {}", vehicleTypeDTO);
        if (vehicleTypeDTO.getId() != null) {
            throw new BadRequestException("A new vehicleType cannot already have an ID");
        }
        VehicleType result = vehicleTypeService.save(vehicleTypeDTO);
        return ResponseEntity.created(new URI("/api/vehicle-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/vehicle-types")
    public ResponseEntity<VehicleType> updateVehicleType(@Valid @RequestBody VehicleType vehicleTypeDTO) throws URISyntaxException {
        log.debug("REST request to update VehicleType : {}", vehicleTypeDTO);
        if (vehicleTypeDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        VehicleType result = vehicleTypeService.save(vehicleTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vehicleTypeDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/vehicle-types")
    public List<VehicleType> getAllVehicleTypes() {
        log.debug("REST request to get all VehicleTypes");
        return vehicleTypeService.findAll();
    }

    @GetMapping("/vehicle-types/{id}")
    public ResponseEntity<VehicleType> getVehicleType(@PathVariable Long id) {
        log.debug("REST request to get VehicleType : {}", id);
        Optional<VehicleType> vehicleTypeDTO = vehicleTypeService.findOne(id);
        if (vehicleTypeDTO==null){
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        }
        return ResponseEntity.ok(vehicleTypeDTO.get());
    }

    @DeleteMapping("/vehicle-types/{id}")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable Long id) {
        log.debug("REST request to delete VehicleType : {}", id);
        vehicleTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
