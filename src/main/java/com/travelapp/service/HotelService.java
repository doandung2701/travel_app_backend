package com.travelapp.service;


import com.travelapp.model.Hotel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Hotel.
 */
public interface HotelService {


    Hotel save(Hotel hotelDTO);

    List<Hotel> findAll();


    Optional<Hotel> findOne(Long id);

    void delete(Long id);
}
