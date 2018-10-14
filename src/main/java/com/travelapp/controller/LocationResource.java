package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Location;
import com.travelapp.service.LocationService;
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
 * REST controller for managing Location.
 */
@RestController
@RequestMapping("/api")
public class LocationResource {

    private final Logger log = LoggerFactory.getLogger(LocationResource.class);

    private static final String ENTITY_NAME = "location";

    private final LocationService locationService;
    @Autowired
    public LocationResource(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<Location> createLocation(@Valid @RequestBody Location locationDTO) throws URISyntaxException {
        log.debug("REST request to save Location : {}", locationDTO);
        if (locationDTO.getId() != null) {
            throw new BadRequestException("A new location cannot already have an ID");
        }
        Location result = locationService.save(locationDTO);
        return ResponseEntity.created(new URI("/api/locations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/locations")

    public ResponseEntity<Location> updateLocation(@Valid @RequestBody Location locationDTO) throws URISyntaxException {
        log.debug("REST request to update Location : {}", locationDTO);
        if (locationDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Location result = locationService.save(locationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, locationDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        log.debug("REST request to get all Locations");
        return locationService.findAll();
    }


    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> getLocation(@PathVariable Long id) {
        log.debug("REST request to get Location : {}", id);
        Optional<Location> locationDTO = locationService.findOne(id);
        if (locationDTO==null)
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        return ResponseEntity.ok(locationDTO.get());
    }


    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        log.debug("REST request to delete Location : {}", id);
        locationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
