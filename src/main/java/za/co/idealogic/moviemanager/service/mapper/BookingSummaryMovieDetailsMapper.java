package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.BookingSummaryMovieDetailsDTO;

import java.util.List;
import java.util.Set;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reservation} to the UI specific DTO {@link BookingSummaryMovieDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookingSummaryMovieDetailsMapper {

    @Mapping(source = "screening.cinema.name", target = "cinemaName")
    @Mapping(source = "screening.movie.name", target = "movieName")
    @Mapping(source = "screening.startTime", target = "startTime")
    @Mapping(source = "screening.cinema.venue.name", target = "venueName")
    @Mapping(source = "seat.number", target = "seatNumber")
    BookingSummaryMovieDetailsDTO toDto(Reservation entity);
    
    List<BookingSummaryMovieDetailsDTO> toDto(Set<Reservation> entityList);

}
