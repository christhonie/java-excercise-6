package za.co.idealogic.moviemanager.service.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import za.co.idealogic.moviemanager.domain.enumeration.PaymentType;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Booking} summary screen. 
 * Only show those fields of importance on that screen.
 */
@JsonPropertyOrder({"referenceNumber", "customer"})
public class BookingSummaryDTO implements Serializable {
    
	private static final long serialVersionUID = -720388265384418796L;

    private String customer;

    private String referenceNumber;

    private PaymentType paymentType;
    
    private List<BookingSummaryMovieDetailsDTO> movies;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

	public List<BookingSummaryMovieDetailsDTO> getMovies() {
		return movies;
	}

	public void setMovies(List<BookingSummaryMovieDetailsDTO> movies) {
		this.movies = movies;
	}
}
