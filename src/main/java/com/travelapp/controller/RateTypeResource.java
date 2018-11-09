package com.travelapp.controller;


import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.RateType;
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
public class RateTypeResource {

    private final Logger log = LoggerFactory.getLogger(RateTypeResource.class);

    private static final String ENTITY_NAME = "rateTour";

    private final RateTourService rateTourService;
    @Autowired
    public RateTypeResource(RateTourService rateTourService) {
        this.rateTourService = rateTourService;
    }


    @PostMapping("/ratetype")
    public ResponseEntity<RateType> createRateTour(@RequestBody RateType rateTypeDTO) throws URISyntaxException {
        log.debug("REST request to save RateType : {}", rateTypeDTO);
        if (rateTypeDTO.getId() != null) {
            throw new BadRequestException("A new rateTour cannot already have an ID");
        }
        RateType result = rateTourService.save(rateTypeDTO);
        return ResponseEntity.created(new URI("/api/rate-tours/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/ratetype")
    public ResponseEntity<RateType> updateRateTour(@RequestBody RateType rateTypeDTO) throws URISyntaxException {
        log.debug("REST request to update RateType : {}", rateTypeDTO);
        if (rateTypeDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        RateType result = rateTourService.save(rateTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rateTypeDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/ratetype")
    public List<RateType> getAllRateTours() {
        log.debug("REST request to get all RateTours");
        return rateTourService.findAll();
    }


    @GetMapping("/ratetype/{id}")
    public ResponseEntity<RateType> getRateTour(@PathVariable Long id) {
        log.debug("REST request to get RateType : {}", id);
        Optional<RateType> rateTourDTO = rateTourService.findOne(id);
        if (rateTourDTO==null)
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        return ResponseEntity.ok(rateTourDTO.get());
    }


    @DeleteMapping("/ratetype/{id}")
    public ResponseEntity<Void> deleteRateTour(@PathVariable Long id) {
        log.debug("REST request to delete RateType : {}", id);
        rateTourService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
