package za.co.idealogic.moviemanager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * A Movie.
 */
@Entity
@Table(name = "movie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "year")
    private Long year;

    @Column(name = "running_time")
    private Duration runningTime;

    @Size(max = 10)
    @Column(name = "rating", length = 10)
    private String rating;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Size(max = 200)
    @Column(name = "imdb_url", length = 200)
    private String imdbUrl;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Genre genre;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private Person director;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "movie_actor",
               joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
    private Set<Person> actors = new HashSet<>();

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

    public Movie name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Movie description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getYear() {
        return year;
    }

    public Movie year(Long year) {
        this.year = year;
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public Movie runningTime(Duration runningTime) {
        this.runningTime = runningTime;
        return this;
    }

    public void setRunningTime(Duration runningTime) {
        this.runningTime = runningTime;
    }

    public String getRating() {
        return rating;
    }

    public Movie rating(String rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public byte[] getImage() {
        return image;
    }

    public Movie image(byte[] image) {
        this.image = image;
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public Movie imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImdbUrl() {
        return imdbUrl;
    }

    public Movie imdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
        return this;
    }

    public void setImdbUrl(String imdbUrl) {
        this.imdbUrl = imdbUrl;
    }

    public Genre getGenre() {
        return genre;
    }

    public Movie genre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Person getDirector() {
        return director;
    }

    public Movie director(Person person) {
        this.director = person;
        return this;
    }

    public void setDirector(Person person) {
        this.director = person;
    }

    public Set<Person> getActors() {
        return actors;
    }

    public Movie actors(Set<Person> people) {
        this.actors = people;
        return this;
    }

    public Movie addActor(Person person) {
        this.actors.add(person);
        person.getMovies().add(this);
        return this;
    }

    public Movie removeActor(Person person) {
        this.actors.remove(person);
        person.getMovies().remove(this);
        return this;
    }

    public void setActors(Set<Person> people) {
        this.actors = people;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)) {
            return false;
        }
        return id != null && id.equals(((Movie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Movie{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", year=" + getYear() +
            ", runningTime='" + getRunningTime() + "'" +
            ", rating='" + getRating() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", imdbUrl='" + getImdbUrl() + "'" +
            "}";
    }
}
