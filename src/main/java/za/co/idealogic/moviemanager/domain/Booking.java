package za.co.idealogic.moviemanager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import za.co.idealogic.moviemanager.domain.enumeration.PaymentType;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "reference_number", nullable = false)
    private String referenceNumber;

    @NotNull
    @Column(name = "created", nullable = false)
    private Instant created;

    @NotNull
    @Size(max = 100)
    @Column(name = "customer", length = 100, nullable = false)
    private String customer;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @OneToMany(mappedBy = "booking")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Reservation> reservations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public Booking referenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Instant getCreated() {
        return created;
    }

    public Booking created(Instant created) {
        this.created = created;
        return this;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public String getCustomer() {
        return customer;
    }

    public Booking customer(String customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Booking paymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public Booking reservations(Set<Reservation> reservations) {
        this.reservations = reservations;
        return this;
    }

    public Booking addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setBooking(this);
        return this;
    }

    public Booking removeReservation(Reservation reservation) {
        this.reservations.remove(reservation);
        reservation.setBooking(null);
        return this;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Booking)) {
            return false;
        }
        return id != null && id.equals(((Booking) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            ", created='" + getCreated() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            "}";
    }
}
