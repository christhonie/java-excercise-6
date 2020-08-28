package za.co.idealogic.moviemanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Reservation.
 */
@Entity
@Table(name = "reservation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "reservations", allowSetters = true)
    private Screening screening;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "reservations", allowSetters = true)
    private Seat seat;

    @ManyToOne
    @JsonIgnoreProperties(value = "reservations", allowSetters = true)
    private Booking booking;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public Reservation screening(Screening screening) {
        this.screening = screening;
        return this;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public Reservation seat(Seat seat) {
        this.seat = seat;
        return this;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Booking getBooking() {
        return booking;
    }

    public Reservation booking(Booking booking) {
        this.booking = booking;
        return this;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }
        return id != null && id.equals(((Reservation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Reservation{" +
            "id=" + getId() +
            "}";
    }
}
