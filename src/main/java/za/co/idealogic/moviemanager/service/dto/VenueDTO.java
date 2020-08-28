package za.co.idealogic.moviemanager.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link za.co.idealogic.moviemanager.domain.Venue} entity.
 */
public class VenueDTO implements Serializable {
    
	private static final long serialVersionUID = -4264680682182425673L;

	private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String email;

    @NotNull
    @Size(max = 50)
    private String town;

    @Size(max = 50)
    private String province;

    @Size(max = 20)
    private String phone;

    @Size(max = 200)
    private String urlgmaps;

    private Double latitude;

    private Double longitude;

    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlgmaps() {
        return urlgmaps;
    }

    public void setUrlgmaps(String urlgmaps) {
        this.urlgmaps = urlgmaps;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenueDTO)) {
            return false;
        }

        return id != null && id.equals(((VenueDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenueDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", town='" + getTown() + "'" +
            ", province='" + getProvince() + "'" +
            ", phone='" + getPhone() + "'" +
            ", urlgmaps='" + getUrlgmaps() + "'" +
            ", latitude=" + getLatitude() +
            ", longitude=" + getLongitude() +
            "}";
    }
}
