package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.BookingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Booking} and its DTO {@link BookingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BookingMapper extends EntityMapper<BookingDTO, Booking> {


    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "removeReservation", ignore = true)
    Booking toEntity(BookingDTO bookingDTO);

    default Booking fromId(Long id) {
        if (id == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(id);
        return booking;
    }
}
