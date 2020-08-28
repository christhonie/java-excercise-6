package za.co.idealogic.moviemanager.repository;

import za.co.idealogic.moviemanager.domain.Genre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Genre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
