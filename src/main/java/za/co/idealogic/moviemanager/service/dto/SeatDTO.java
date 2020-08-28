package za.co.idealogic.moviemanager.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Seat} entity.
 */
public class SeatDTO implements Serializable {
    
	private static final long serialVersionUID = 184100784398042078L;

	private Long id;

    @NotNull
    @Size(max = 5)
    private String number;

    @NotNull
    private Long rowNumber;

    @NotNull
    private Long seatNumer;


    private Long cinemaId;

    private String cinemaName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Long getSeatNumer() {
        return seatNumer;
    }

    public void setSeatNumer(Long seatNumer) {
        this.seatNumer = seatNumer;
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
        if (!(o instanceof SeatDTO)) {
            return false;
        }

        return id != null && id.equals(((SeatDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SeatDTO{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            ", rowNumber=" + getRowNumber() +
            ", seatNumer=" + getSeatNumer() +
            ", cinemaId=" + getCinemaId() +
            ", cinemaName='" + getCinemaName() + "'" +
            "}";
    }
}
