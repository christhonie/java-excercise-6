package za.co.idealogic.moviemanager.service.mapper;


import za.co.idealogic.moviemanager.domain.*;
import za.co.idealogic.moviemanager.service.dto.ReservationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Reservation} and its DTO {@link ReservationDTO}.
 */
@Mapper(componentModel = "spring", uses = {ScreeningMapper.class, SeatMapper.class, BookingMapper.class})
public interface ReservationMapper extends EntityMapper<ReservationDTO, Reservation> {

    @Mapping(source = "screening.id", target = "screeningId")
    @Mapping(source = "screening.startTime", target = "screeningStartTime")
    @Mapping(source = "seat.id", target = "seatId")
    @Mapping(source = "seat.number", target = "seatNumber")
    @Mapping(source = "booking.id", target = "bookingId")
    @Mapping(source = "booking.referenceNumber", target = "bookingReferenceNumber")
    ReservationDTO toDto(Reservation reservation);

    @Mapping(source = "screeningId", target = "screening")
    @Mapping(source = "seatId", target = "seat")
    @Mapping(source = "bookingId", target = "booking")
    Reservation toEntity(ReservationDTO reservationDTO);

    default Reservation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reservation reservation = new Reservation();
        reservation.setId(id);
        return reservation;
    }
}
