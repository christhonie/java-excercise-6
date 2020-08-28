package za.co.idealogic.moviemanager.repository;

import za.co.idealogic.moviemanager.domain.Venue;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Venue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {
}
