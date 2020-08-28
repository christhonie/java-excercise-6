package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.PersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Person} and its DTO {@link PersonDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {


    @Mapping(target = "movies", ignore = true)
    @Mapping(target = "removeMovie", ignore = true)
    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
