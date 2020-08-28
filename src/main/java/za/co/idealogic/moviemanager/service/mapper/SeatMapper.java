package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.SeatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Seat} and its DTO {@link SeatDTO}.
 */
@Mapper(componentModel = "spring", uses = {CinemaMapper.class})
public interface SeatMapper extends EntityMapper<SeatDTO, Seat> {

    @Mapping(source = "cinema.id", target = "cinemaId")
    @Mapping(source = "cinema.name", target = "cinemaName")
    SeatDTO toDto(Seat seat);

    @Mapping(source = "cinemaId", target = "cinema")
    Seat toEntity(SeatDTO seatDTO);

    default Seat fromId(Long id) {
        if (id == null) {
            return null;
        }
        Seat seat = new Seat();
        seat.setId(id);
        return seat;
    }
}
