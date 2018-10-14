package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Hotel;
import com.travelapp.service.HotelService;
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
 * REST controller for managing Hotel.
 */
@RestController
@RequestMapping("/api")
public class HotelResource {

    private final Logger log = LoggerFactory.getLogger(HotelResource.class);

    private static final String ENTITY_NAME = "hotel";

    private final HotelService hotelService;
    @Autowired
    public HotelResource(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/hotels")
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotelDTO) throws URISyntaxException {
        log.debug("REST request to save Hotel : {}", hotelDTO);
        if (hotelDTO.getId() != null) {
            throw new BadRequestException("A new hotel cannot already have an ID");
        }
        Hotel result = hotelService.save(hotelDTO);
        return ResponseEntity.created(new URI("/api/hotels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/hotels")
    public ResponseEntity<Hotel> updateHotel(@Valid @RequestBody Hotel hotelDTO) throws URISyntaxException {
        log.debug("REST request to update Hotel : {}", hotelDTO);
        if (hotelDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Hotel result = hotelService.save(hotelDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hotelDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/hotels")
    public List<Hotel> getAllHotels() {
        log.debug("REST request to get all Hotels");
        return hotelService.findAll();
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable Long id) {
        log.debug("REST request to get Hotel : {}", id);
        Optional<Hotel> hotelDTO = hotelService.findOne(id);
        if (hotelDTO == null) {
            throw new ResourceNotFoundException(ENTITY_NAME, "id", id);
        }
        return ResponseEntity.ok(hotelDTO.get());

    }
    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        log.debug("REST request to delete Hotel : {}", id);
        hotelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
