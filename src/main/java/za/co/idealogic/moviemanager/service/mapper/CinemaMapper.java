package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.CinemaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cinema} and its DTO {@link CinemaDTO}.
 */
@Mapper(componentModel = "spring", uses = {VenueMapper.class})
public interface CinemaMapper extends EntityMapper<CinemaDTO, Cinema> {

    @Mapping(source = "venue.id", target = "venueId")
    @Mapping(source = "venue.name", target = "venueName")
    CinemaDTO toDto(Cinema cinema);

    @Mapping(source = "venueId", target = "venue")
    Cinema toEntity(CinemaDTO cinemaDTO);

    default Cinema fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cinema cinema = new Cinema();
        cinema.setId(id);
        return cinema;
    }
}
