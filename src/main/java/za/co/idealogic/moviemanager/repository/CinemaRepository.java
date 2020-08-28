package za.co.idealogic.moviemanager.repository;

import za.co.idealogic.moviemanager.domain.Cinema;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cinema entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
