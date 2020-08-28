package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.VenueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Venue} and its DTO {@link VenueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VenueMapper extends EntityMapper<VenueDTO, Venue> {



    default Venue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Venue venue = new Venue();
        venue.setId(id);
        return venue;
    }
}
