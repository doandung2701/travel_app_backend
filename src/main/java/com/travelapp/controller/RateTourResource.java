package com.travelapp.controller;


import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.RateTour;
import com.travelapp.service.RateTourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RateTourResource {

    private final Logger log = LoggerFactory.getLogger(RateTourResource.class);

    private static final String ENTITY_NAME = "rateTour";

    private final RateTourService rateTourService;
    @Autowired
    public RateTourResource(RateTourService rateTourService) {
        this.rateTourService = rateTourService;
    }


    @PostMapping("/rate-tours")
    public ResponseEntity<RateTour> createRateTour(@RequestBody RateTour rateTourDTO) throws URISyntaxException {
        log.debug("REST request to save RateTour : {}", rateTourDTO);
        if (rateTourDTO.getId() != null) {
            throw new BadRequestException("A new rateTour cannot already have an ID");
        }
        RateTour result = rateTourService.save(rateTourDTO);
        return ResponseEntity.created(new URI("/api/rate-tours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/rate-tours")
    public ResponseEntity<RateTour> updateRateTour(@RequestBody RateTour rateTourDTO) throws URISyntaxException {
        log.debug("REST request to update RateTour : {}", rateTourDTO);
        if (rateTourDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        RateTour result = rateTourService.save(rateTourDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rateTourDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/rate-tours")
    public List<RateTour> getAllRateTours() {
        log.debug("REST request to get all RateTours");
        return rateTourService.findAll();
    }


    @GetMapping("/rate-tours/{id}")
    public ResponseEntity<RateTour> getRateTour(@PathVariable Long id) {
        log.debug("REST request to get RateTour : {}", id);
        Optional<RateTour> rateTourDTO = rateTourService.findOne(id);
        if (rateTourDTO==null)
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        return ResponseEntity.ok(rateTourDTO.get());
    }


    @DeleteMapping("/rate-tours/{id}")
    public ResponseEntity<Void> deleteRateTour(@PathVariable Long id) {
        log.debug("REST request to delete RateTour : {}", id);
        rateTourService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
