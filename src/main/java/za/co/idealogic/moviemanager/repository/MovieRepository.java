package za.co.idealogic.moviemanager.repository;

import za.co.idealogic.moviemanager.domain.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Movie entity.
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select distinct movie from Movie movie left join fetch movie.actors",
        countQuery = "select count(distinct movie) from Movie movie")
    Page<Movie> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct movie from Movie movie left join fetch movie.actors")
    List<Movie> findAllWithEagerRelationships();

    @Query("select movie from Movie movie left join fetch movie.actors where movie.id =:id")
    Optional<Movie> findOneWithEagerRelationships(@Param("id") Long id);

	//List<Movie> findByRunningTimeGreaterThanAndfindByRunningTimeLessThan(Long greaterThan, Long lessThan);

	List<Movie> findByRunningTimeBetween(Duration greaterThan, Duration lessThan);
	
	List<Movie> findByRunningTimeBetweenOrderByRunningTimeDesc();
	
	List<Movie> findByRunningTimeGreaterThan(Duration greaterThan);

	List<Movie> findByRunningTimeLessThan(Duration lessThan);

}