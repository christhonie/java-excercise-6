package za.co.idealogic.moviemanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Cinema.
 */
@Entity
@Table(name = "cinema")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Cinema implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "cinemas", allowSetters = true)
    private Venue venue;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Cinema name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Venue getVenue() {
        return venue;
    }

    public Cinema venue(Venue venue) {
        this.venue = venue;
        return this;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cinema)) {
            return false;
        }
        return id != null && id.equals(((Cinema) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cinema{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
