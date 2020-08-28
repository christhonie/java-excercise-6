package za.co.idealogic.moviemanager.service.dto;

import java.time.Duration;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Lob;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Movie} entity.
 */
public class MovieDTO implements Serializable {
    
	private static final long serialVersionUID = -2693923702618021510L;

	private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 200)
    private String description;

    private Long year;

    private Duration runningTime;

    @Size(max = 10)
    private String rating;

    @Lob
    private byte[] image;

    private String imageContentType;
    @Size(max = 200)
    private String imdbUrl;


    private Long genreId;

    private String genreName;

    private Long directorId;

    private String directorName;
    private Set<PersonDTO> actors = new HashSet<>();
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Duration runningTime) {
        this.runningTime = runningTime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long personId) {
        this.directorId = personId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String personName) {
        this.directorName = personName;
    }

    public Set<PersonDTO> getActors() {
        return actors;
    }

    public void setActors(Set<PersonDTO> people) {
        this.actors = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MovieDTO)) {
            return false;
        }

        return id != null && id.equals(((MovieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MovieDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", year=" + getYear() +
            ", runningTime='" + getRunningTime() + "'" +
            ", rating='" + getRating() + "'" +
            ", image='" + getImage() + "'" +
            ", imdbUrl='" + getImdbUrl() + "'" +
            ", genreId=" + getGenreId() +
            ", genreName='" + getGenreName() + "'" +
            ", directorId=" + getDirectorId() +
            ", directorName='" + getDirectorName() + "'" +
            ", actors='" + getActors() + "'" +
            "}";
    }
}
