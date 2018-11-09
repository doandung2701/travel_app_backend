package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Rate;
import com.travelapp.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RateController {
    private final Logger log = LoggerFactory.getLogger(BookingResource.class);

    private static final String ENTITY_NAME = "rating";
    @Autowired
    private  RateService rateService;
    @PostMapping("/rate")
    public ResponseEntity<Rate> createRating(@RequestBody Rate rateDTO) throws URISyntaxException {
        log.debug("REST request to save Rate : {}", rateDTO);
        if (rateDTO.getId() != null) {
            throw new BadRequestException("A new booking cannot already have an ID");
        }
        Rate result = rateService.save(rateDTO);
        return ResponseEntity.created(new URI("/api/rates/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/rate")
    public ResponseEntity<Rate> updateRating(@RequestBody Rate rateDTO) throws URISyntaxException {
        log.debug("REST request to update Rate : {}", rateDTO);
        if (rateDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Rate result = rateService.save(rateDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rateDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/rate")
    public List<Rate> getAllRates(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Bookings");
        return rateService.findAll();
    }

    @GetMapping("/rate/{id}")
    public ResponseEntity<Rate> getRating(@PathVariable Long id) {
        log.debug("REST request to get Rate : {}", id);
        Optional<Rate> rateDTO = rateService.findOne(id);
        if (rateDTO==null){
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        }
        return ResponseEntity.ok(rateDTO.get());
    }

    @DeleteMapping("/rate/{id}")
    public ResponseEntity<Void> deleteRate(@PathVariable Long id) {
        log.debug("REST request to delete Rate : {}", id);
        rateService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    @GetMapping("/rate/tour/{tourId}")
    public ResponseEntity<?> getRateByTourId(@PathVariable Long tourId){
        log.debug("REST request to get List rate of tour using tourID");
        List<Rate> rates=rateService.findRateByTour(tourId);
        return new ResponseEntity<>(rates, HttpStatus.OK);
    }
}
