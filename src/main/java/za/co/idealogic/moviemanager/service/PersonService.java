package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Person;
import za.co.idealogic.moviemanager.repository.PersonRepository;
import za.co.idealogic.moviemanager.service.dto.PersonDTO;
import za.co.idealogic.moviemanager.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonService.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    /**
     * Save a person.
     *
     * @param personDTO the entity to save.
     * @return the persisted entity.
     */
    public PersonDTO save(PersonDTO personDTO) {
        log.debug("Request to save Person : {}", personDTO);
        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    /**
     * Get all the people.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all People");
        return personRepository.findAll(pageable)
            .map(personMapper::toDto);
    }


    /**
     * Get one person by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findById(id)
            .map(personMapper::toDto);
    }

    /**
     * Delete the person by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }
    
    
    /**
     * Lookup a person by name. If found, return that person. If not found create a new one.
     * @param name The name of the person to lookup or create 
     * @return Either a current or new {@link Person}
     */
    public Person lookupOrCreate(String name) {
		return personRepository.findByName(name).orElse(new Person().name(name));
    	
//    	Optional<Person> result = personRepository.findByName(name);
//    	
//    	if(!result.isPresent()) {
//    		Person newPerson = new Person().name(name);
//    		return newPerson;
//    	} else
//    		return result.get();
	}
}
