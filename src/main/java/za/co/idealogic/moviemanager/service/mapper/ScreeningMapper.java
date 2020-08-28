package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.ScreeningDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Screening} and its DTO {@link ScreeningDTO}.
 */
@Mapper(componentModel = "spring", uses = {MovieMapper.class, CinemaMapper.class})
public interface ScreeningMapper extends EntityMapper<ScreeningDTO, Screening> {

    @Mapping(source = "movie.id", target = "movieId")
    @Mapping(source = "movie.name", target = "movieName")
    @Mapping(source = "cinema.id", target = "cinemaId")
    @Mapping(source = "cinema.name", target = "cinemaName")
    ScreeningDTO toDto(Screening screening);

    @Mapping(source = "movieId", target = "movie")
    @Mapping(source = "cinemaId", target = "cinema")
    Screening toEntity(ScreeningDTO screeningDTO);

    default Screening fromId(Long id) {
        if (id == null) {
            return null;
        }
        Screening screening = new Screening();
        screening.setId(id);
        return screening;
    }
}
