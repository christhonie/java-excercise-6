package za.co.idealogic.moviemanager.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Genre} entity.
 */
public class GenreDTO implements Serializable {
    
	private static final long serialVersionUID = 2711858245615365256L;

	private Long id;

    @NotNull
    @Size(max = 100)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenreDTO)) {
            return false;
        }

        return id != null && id.equals(((GenreDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GenreDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
