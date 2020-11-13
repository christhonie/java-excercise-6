package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Booking;
import za.co.idealogic.moviemanager.domain.Reservation;
import za.co.idealogic.moviemanager.repository.BookingRepository;
import za.co.idealogic.moviemanager.service.dto.BookingDTO;
import za.co.idealogic.moviemanager.service.dto.BookingSummaryDTO;
import za.co.idealogic.moviemanager.service.dto.BookingSummaryMovieDetailsDTO;
import za.co.idealogic.moviemanager.service.mapper.BookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        //Query - Get the data from the DB
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        
        if(optionalBooking.isPresent()) {
            //Map the Entity to the new BookingSummaryDTO
        	final BookingSummaryDTO bookingSummaryDTO = new BookingSummaryDTO();
        	final Booking booking = optionalBooking.get();

        	bookingSummaryDTO.setCustomer(booking.getCustomer());
        	bookingSummaryDTO.setPaymentType(booking.getPaymentType());
        	bookingSummaryDTO.setReferenceNumber(booking.getReferenceNumber());
        	
            //Loop through all the detail records
        	List<BookingSummaryMovieDetailsDTO> movies = new ArrayList<>();

        	//Stuff to set each item in the list
        	for(Reservation reservation : booking.getReservations()) {
        		BookingSummaryMovieDetailsDTO detail = new BookingSummaryMovieDetailsDTO();
        		detail.setCinemaName(reservation.getScreening().getCinema().getName());
        		detail.setMovieName(reservation.getScreening().getMovie().getName());
        		detail.setStartTime(reservation.getScreening().getStartTime());
        		detail.setVenueName(reservation.getScreening().getCinema().getVenue().getName());
        		detail.setSeatNumber(reservation.getSeat().getNumber());
        		
        		movies.add(detail);
        	}
        	
        	bookingSummaryDTO.setMovies(movies);
        	
        	return Optional.of(bookingSummaryDTO);  	
        	
        } else {
        	return Optional.empty();
        }
    }
}
