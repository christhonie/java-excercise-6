package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Booking;
import za.co.idealogic.moviemanager.domain.Reservation;
import za.co.idealogic.moviemanager.repository.BookingRepository;
import za.co.idealogic.moviemanager.service.dto.BookingDTO;
import za.co.idealogic.moviemanager.service.dto.BookingSummaryDTO;
import za.co.idealogic.moviemanager.service.dto.BookingSummaryMovieDetailsDTO;
import za.co.idealogic.moviemanager.service.mapper.BookingMapper;
import za.co.idealogic.moviemanager.service.mapper.BookingSummaryMapper;
import za.co.idealogic.moviemanager.service.mapper.BookingSummaryMovieDetailsMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Booking}.
 */
@Service
@Transactional
public class BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;

    private final BookingMapper bookingMapper;
    
    @Autowired
    private BookingSummaryMapper bookingSummaryMapper;
    
    @Autowired
    private BookingSummaryMovieDetailsMapper bookingSummaryMovieDetailMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    /**
     * Save a booking.
     *
     * @param bookingDTO the entity to save.
     * @return the persisted entity.
     */
    public BookingDTO save(BookingDTO bookingDTO) {
        log.debug("Request to save Booking : {}", bookingDTO);
        Booking booking = bookingMapper.toEntity(bookingDTO);
        booking = bookingRepository.save(booking);
        return bookingMapper.toDto(booking);
    }

    /**
     * Get all the bookings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BookingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bookings");
        return bookingRepository.findAll(pageable)
            .map(bookingMapper::toDto);
    }


    /**
     * Get one booking by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BookingDTO> findOne(Long id) {
        log.debug("Request to get Booking : {}", id);
        return bookingRepository.findById(id)
            .map(bookingMapper::toDto);
    }

    /**
     * Delete the booking by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Booking : {}", id);
        bookingRepository.deleteById(id);
    }
    
    public Optional<BookingSummaryDTO> findBookingSummary(Long id) {
        log.debug("Request to get Booking : {}", id);

        return bookingRepository.findById(id).map(bookingSummaryMapper::toDto);
    }
}
