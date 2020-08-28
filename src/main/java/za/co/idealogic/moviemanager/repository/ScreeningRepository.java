package za.co.idealogic.moviemanager.repository;

import za.co.idealogic.moviemanager.domain.Screening;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Screening entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
