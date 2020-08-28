package za.co.idealogic.moviemanager.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Screening} entity.
 */
public class ScreeningDTO implements Serializable {
    
	private static final long serialVersionUID = 7735169910491370492L;

	private Long id;

    @NotNull
    private Instant startTime;

    private Instant endTime;


    private Long movieId;

    private String movieName;

    private Long cinemaId;

    private String cinemaName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScreeningDTO)) {
            return false;
        }

        return id != null && id.equals(((ScreeningDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ScreeningDTO{" +
            "id=" + getId() +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", movieId=" + getMovieId() +
            ", movieName='" + getMovieName() + "'" +
            ", cinemaId=" + getCinemaId() +
            ", cinemaName='" + getCinemaName() + "'" +
            "}";
    }
}
