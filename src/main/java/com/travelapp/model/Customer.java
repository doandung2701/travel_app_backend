package com.travelapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelapp.model.enumeration.GENDER;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(min = 6)
    @Column(name = "jhi_password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private GENDER gender;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Size(min = 2)
    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comment> comments = new HashSet<>();
    @OneToMany(mappedBy = "customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Booking> bookings = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public Customer password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Customer birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public GENDER getGender() {
        return gender;
    }

    public Customer gender(GENDER gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public Customer phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public Customer address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Customer comments(Set<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public Customer addComment(Comment comment) {
        this.comments.add(comment);
        comment.setCustomer(this);
        return this;
    }

    public Customer removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setCustomer(null);
        return this;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }

    public Customer bookings(Set<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Customer addBooking(Booking booking) {
        this.bookings.add(booking);
        booking.setCustomer(this);
        return this;
    }

    public Customer removeBooking(Booking booking) {
        this.bookings.remove(booking);
        booking.setCustomer(null);
        return this;
    }

    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
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
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", gender='" + getGender() + "'" +
            ", phone='" + getPhone() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
