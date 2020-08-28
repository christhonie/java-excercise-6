package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Cinema;
import za.co.idealogic.moviemanager.repository.CinemaRepository;
import za.co.idealogic.moviemanager.service.dto.CinemaDTO;
import za.co.idealogic.moviemanager.service.mapper.CinemaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cinema}.
 */
@Service
@Transactional
public class CinemaService {

    private final Logger log = LoggerFactory.getLogger(CinemaService.class);

    private final CinemaRepository cinemaRepository;

    private final CinemaMapper cinemaMapper;

    public CinemaService(CinemaRepository cinemaRepository, CinemaMapper cinemaMapper) {
        this.cinemaRepository = cinemaRepository;
        this.cinemaMapper = cinemaMapper;
    }

    /**
     * Save a cinema.
     *
     * @param cinemaDTO the entity to save.
     * @return the persisted entity.
     */
    public CinemaDTO save(CinemaDTO cinemaDTO) {
        log.debug("Request to save Cinema : {}", cinemaDTO);
        Cinema cinema = cinemaMapper.toEntity(cinemaDTO);
        cinema = cinemaRepository.save(cinema);
        return cinemaMapper.toDto(cinema);
    }

    /**
     * Get all the cinemas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CinemaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Cinemas");
        return cinemaRepository.findAll(pageable)
            .map(cinemaMapper::toDto);
    }


    /**
     * Get one cinema by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CinemaDTO> findOne(Long id) {
        log.debug("Request to get Cinema : {}", id);
        return cinemaRepository.findById(id)
            .map(cinemaMapper::toDto);
    }

    /**
     * Delete the cinema by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Cinema : {}", id);
        cinemaRepository.deleteById(id);
    }
}
