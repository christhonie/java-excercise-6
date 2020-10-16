package za.co.idealogic.moviemanager.repository;

import za.co.idealogic.moviemanager.domain.Person;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Person entity.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
	
	/**
	 * Find a person my their name
	 * @param name The name of the person.
	 * @return Return the found record as an {@link Optional}
	 */
	Optional<Person> findByName(String name);	
}
