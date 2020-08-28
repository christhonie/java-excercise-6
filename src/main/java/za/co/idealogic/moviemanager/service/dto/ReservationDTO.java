package za.co.idealogic.moviemanager.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Reservation} entity.
 */
public class ReservationDTO implements Serializable {
    
	private static final long serialVersionUID = -4355419885959209923L;

	private Long id;

    private Long screeningId;

    private String screeningStartTime;

    private Long seatId;

    private String seatNumber;

    private Long bookingId;

    private String bookingReferenceNumber;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(Long screeningId) {
        this.screeningId = screeningId;
    }

    public String getScreeningStartTime() {
        return screeningStartTime;
    }

    public void setScreeningStartTime(String screeningStartTime) {
        this.screeningStartTime = screeningStartTime;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingReferenceNumber() {
        return bookingReferenceNumber;
    }

    public void setBookingReferenceNumber(String bookingReferenceNumber) {
        this.bookingReferenceNumber = bookingReferenceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationDTO)) {
            return false;
        }

        return id != null && id.equals(((ReservationDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReservationDTO{" +
            "id=" + getId() +
            ", screeningId=" + getScreeningId() +
            ", screeningStartTime='" + getScreeningStartTime() + "'" +
            ", seatId=" + getSeatId() +
            ", seatNumber='" + getSeatNumber() + "'" +
            ", bookingId=" + getBookingId() +
            ", bookingReferenceNumber='" + getBookingReferenceNumber() + "'" +
            "}";
    }
}
