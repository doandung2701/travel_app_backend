package com.travelapp.controller;

import com.travelapp.controller.util.HeaderUtil;
import com.travelapp.exception.BadRequestException;
import com.travelapp.exception.ResourceNotFoundException;
import com.travelapp.model.Gallery;
import com.travelapp.model.Location;
import com.travelapp.service.GalleryService;
import com.travelapp.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Gallery.
 */
@RestController
@RequestMapping("/api")
public class GalleryResource {

    private final Logger log = LoggerFactory.getLogger(GalleryResource.class);

    private static final String ENTITY_NAME = "gallery";

    private final GalleryService galleryService;
    private  final LocationService locationService;
    @Autowired
    public GalleryResource(GalleryService galleryService, LocationService locationService) {
        this.galleryService = galleryService;
        this.locationService=locationService;
    }

    @PostMapping("/galleries")
    public ResponseEntity<Gallery> createGallery(MultipartFile picture,Long locationId) throws URISyntaxException, IOException {
       Gallery galleryDTO=new Gallery();
       Location location=locationService.findOne(locationId).get();
       galleryDTO.setLocation(location);
       galleryDTO.setPicture(picture.getBytes());
        Gallery result = galleryService.save(galleryDTO);
        return ResponseEntity.created(new URI("/api/galleries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(null);
    }

    @PutMapping("/galleries")
    public ResponseEntity<Gallery> updateGallery(@Valid @RequestBody Gallery galleryDTO) throws URISyntaxException {
        log.debug("REST request to update Gallery : {}", galleryDTO);
        if (galleryDTO.getId() == null) {
            throw new BadRequestException("Invalid id");
        }
        Gallery result = galleryService.save(galleryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, galleryDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/galleries")
    public List<Gallery> getAllGalleries() {
        log.debug("REST request to get all Galleries");
        return galleryService.findAll();
    }

    @GetMapping("/galleries/{id}")
    public ResponseEntity<Gallery> getGallery(@PathVariable Long id) {
        log.debug("REST request to get Gallery : {}", id);
        Optional<Gallery> galleryDTO = galleryService.findOne(id);
        if (galleryDTO==null){
            throw new ResourceNotFoundException(ENTITY_NAME,"id",id);
        }
        return ResponseEntity.ok(galleryDTO.get());
    }

    @DeleteMapping("/galleries/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long id) {
        log.debug("REST request to delete Gallery : {}", id);
        galleryService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
