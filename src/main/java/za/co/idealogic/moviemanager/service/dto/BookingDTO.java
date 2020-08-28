package za.co.idealogic.moviemanager.service.dto;

import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import za.co.idealogic.moviemanager.domain.enumeration.PaymentType;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Booking} entity.
 */
public class BookingDTO implements Serializable {
    
	private static final long serialVersionUID = 2814075131146453627L;

	private Long id;

    @NotNull
    private String referenceNumber;

    @NotNull
    private Instant created;

    @NotNull
    @Size(max = 100)
    private String customer;

    @NotNull
    private PaymentType paymentType;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookingDTO)) {
            return false;
        }

        return id != null && id.equals(((BookingDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookingDTO{" +
            "id=" + getId() +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            ", created='" + getCreated() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            "}";
    }
}
