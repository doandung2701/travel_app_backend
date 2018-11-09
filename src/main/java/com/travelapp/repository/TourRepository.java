package com.travelapp.repository;

import com.travelapp.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Tour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {

    @Query(value = "select distinct tour from Tour tour left join fetch tour.rates left join fetch tour.locations left join  fetch tour.comments",
        countQuery = "select count(distinct tour) from Tour tour")
    Page<Tour> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct tour from Tour tour left join fetch tour.rates left join fetch tour.locations left join  fetch tour.comments" )
    List<Tour> findAllWithEagerRelationships();

    @Query("select tour from Tour tour left join fetch tour.rates left join fetch tour.locations left join  fetch tour.comments where tour.id =:id")
    Optional<Tour> findOneWithEagerRelationships(@Param("id") Long id);
    List<Tour> findAllByCategoryId(long categoryId);
}
