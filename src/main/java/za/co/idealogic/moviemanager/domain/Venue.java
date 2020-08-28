package za.co.idealogic.moviemanager.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Venue.
 */
@Entity
@Table(name = "venue")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Venue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "town", length = 50, nullable = false)
    private String town;

    @Size(max = 50)
    @Column(name = "province", length = 50)
    private String province;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 200)
    @Column(name = "urlgmaps", length = 200)
    private String urlgmaps;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

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

    public Venue name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public Venue email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public Venue town(String town) {
        this.town = town;
        return this;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getProvince() {
        return province;
    }

    public Venue province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public Venue phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUrlgmaps() {
        return urlgmaps;
    }

    public Venue urlgmaps(String urlgmaps) {
        this.urlgmaps = urlgmaps;
        return this;
    }

    public void setUrlgmaps(String urlgmaps) {
        this.urlgmaps = urlgmaps;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Venue latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Venue longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venue)) {
            return false;
        }
        return id != null && id.equals(((Venue) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Venue{" +
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
