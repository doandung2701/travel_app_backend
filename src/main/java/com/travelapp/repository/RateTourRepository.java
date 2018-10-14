package com.travelapp.repository;

import com.travelapp.model.RateTour;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RateTour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RateTourRepository extends JpaRepository<RateTour, Long> {

}
