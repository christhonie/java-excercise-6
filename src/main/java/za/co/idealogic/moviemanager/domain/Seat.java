package za.co.idealogic.moviemanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Seat.
 */
@Entity
@Table(name = "seat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Seat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "number", length = 5, nullable = false)
    private String number;

    @NotNull
    @Column(name = "jhi_row_number", nullable = false)
    private Long rowNumber;

    @NotNull
    @Column(name = "seat_numer", nullable = false)
    private Long seatNumer;

    @ManyToOne
    @JsonIgnoreProperties(value = "seats", allowSetters = true)
    private Cinema cinema;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public Seat number(String number) {
        this.number = number;
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public Seat rowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Long getSeatNumer() {
        return seatNumer;
    }

    public Seat seatNumer(Long seatNumer) {
        this.seatNumer = seatNumer;
        return this;
    }

    public void setSeatNumer(Long seatNumer) {
        this.seatNumer = seatNumer;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Seat cinema(Cinema cinema) {
        this.cinema = cinema;
        return this;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Seat)) {
            return false;
        }
        return id != null && id.equals(((Seat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Seat{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            ", rowNumber=" + getRowNumber() +
            ", seatNumer=" + getSeatNumer() +
            "}";
    }
}
