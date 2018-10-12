package com.travelapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelapp.model.enumeration.RATE;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A RateTour.
 */
@Entity
@Table(name = "rate_tour")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RateTour implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rate")
    private RATE rate;

    @ManyToMany(mappedBy = "rateTours")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Tour> tours = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RATE getRate() {
        return rate;
    }

    public RateTour rate(RATE rate) {
        this.rate = rate;
        return this;
    }

    public void setRate(RATE rate) {
        this.rate = rate;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public RateTour tours(Set<Tour> tours) {
        this.tours = tours;
        return this;
    }

    public RateTour addTour(Tour tour) {
        this.tours.add(tour);
        tour.getRateTours().add(this);
        return this;
    }

    public RateTour removeTour(Tour tour) {
        this.tours.remove(tour);
        tour.getRateTours().remove(this);
        return this;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RateTour rateTour = (RateTour) o;
        if (rateTour.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rateTour.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RateTour{" +
            "id=" + getId() +
            ", rate='" + getRate() + "'" +
            "}";
    }
}
