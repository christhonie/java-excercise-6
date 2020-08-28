package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Seat;
import za.co.idealogic.moviemanager.repository.SeatRepository;
import za.co.idealogic.moviemanager.service.dto.SeatDTO;
import za.co.idealogic.moviemanager.service.mapper.SeatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Seat}.
 */
@Service
@Transactional
public class SeatService {

    private final Logger log = LoggerFactory.getLogger(SeatService.class);

    private final SeatRepository seatRepository;

    private final SeatMapper seatMapper;

    public SeatService(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    /**
     * Save a seat.
     *
     * @param seatDTO the entity to save.
     * @return the persisted entity.
     */
    public SeatDTO save(SeatDTO seatDTO) {
        log.debug("Request to save Seat : {}", seatDTO);
        Seat seat = seatMapper.toEntity(seatDTO);
        seat = seatRepository.save(seat);
        return seatMapper.toDto(seat);
    }

    /**
     * Get all the seats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SeatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Seats");
        return seatRepository.findAll(pageable)
            .map(seatMapper::toDto);
    }


    /**
     * Get one seat by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SeatDTO> findOne(Long id) {
        log.debug("Request to get Seat : {}", id);
        return seatRepository.findById(id)
            .map(seatMapper::toDto);
    }

    /**
     * Delete the seat by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Seat : {}", id);
        seatRepository.deleteById(id);
    }
}
