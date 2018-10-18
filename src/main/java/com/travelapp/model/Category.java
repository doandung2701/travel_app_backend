package com.travelapp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @OneToMany(mappedBy = "category")
    private Set<Booking> bookings=new HashSet<Booking>();

    public Set<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }
    public Category addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setCategory(this);
        return this;
    }

    public Category removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setCategory(null);
        return this;
    }
}
