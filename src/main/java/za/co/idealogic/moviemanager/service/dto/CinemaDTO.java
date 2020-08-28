package za.co.idealogic.moviemanager.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Cinema} entity.
 */
public class CinemaDTO implements Serializable {
    
	private static final long serialVersionUID = -4030083822391840642L;

	private Long id;

    @NotNull
    @Size(max = 50)
    private String name;


    private Long venueId;

    private String venueName;
    
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

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CinemaDTO)) {
            return false;
        }

        return id != null && id.equals(((CinemaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CinemaDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", venueId=" + getVenueId() +
            ", venueName='" + getVenueName() + "'" +
            "}";
    }
}
