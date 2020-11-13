package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.BookingSummaryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Booking} and the UI specific DTO {@link BookingSummaryDTO}.
 */
@Mapper(componentModel = "spring", uses = {BookingSummaryMovieDetailsMapper.class})
public interface BookingSummaryMapper {

	@Mapping(source = "reservations", target = "movies")
    BookingSummaryDTO toDto(Booking entity);


}
