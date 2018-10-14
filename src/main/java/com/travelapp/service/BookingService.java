package com.travelapp.service;

import com.travelapp.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking save(Booking bookingDTO);
    List<Booking> findAll();
    Page<Booking> findAllWithEagerRelationships(Pageable pageable);
    Optional<Booking> findOne(Long id);
    void delete(Long id);
}
