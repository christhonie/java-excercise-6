package za.co.idealogic.moviemanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Screening.
 */
@Entity
@Table(name = "screening")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Screening implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "start_time", nullable = false)
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "screenings", allowSetters = true)
    private Movie movie;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "screenings", allowSetters = true)
    private Cinema cinema;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Screening startTime(Instant startTime) {
        this.startTime = startTime;
        return this;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Screening endTime(Instant endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screening movie(Movie movie) {
        this.movie = movie;
        return this;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public Screening cinema(Cinema cinema) {
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
        if (!(o instanceof Screening)) {
            return false;
        }
        return id != null && id.equals(((Screening) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Screening{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }
}
