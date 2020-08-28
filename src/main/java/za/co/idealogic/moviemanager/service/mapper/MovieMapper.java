package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.MovieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Movie} and its DTO {@link MovieDTO}.
 */
@Mapper(componentModel = "spring", uses = {GenreMapper.class, PersonMapper.class})
public interface MovieMapper extends EntityMapper<MovieDTO, Movie> {

    @Mapping(source = "genre.id", target = "genreId")
    @Mapping(source = "genre.name", target = "genreName")
    @Mapping(source = "director.id", target = "directorId")
    @Mapping(source = "director.name", target = "directorName")
    @Mapping(target = "image", ignore = true)
    MovieDTO toDto(Movie movie);

    @Mapping(source = "genreId", target = "genre")
    @Mapping(source = "directorId", target = "director")
    @Mapping(target = "removeActor", ignore = true)
    Movie toEntity(MovieDTO movieDTO);

    default Movie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Movie movie = new Movie();
        movie.setId(id);
        return movie;
    }
}
