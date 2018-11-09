package com.travelapp.service;

import com.travelapp.model.Rate;
import com.travelapp.model.Tour;

import java.util.List;
import java.util.Optional;

public interface RateService {
    Rate save(Rate rate);

    List<Rate> findAll();


    Optional<Rate> findOne(Long id);

    List<Rate> findRateByTour(Long tourId);

    void delete(Long id);
}
