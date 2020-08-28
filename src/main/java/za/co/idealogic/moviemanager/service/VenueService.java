package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Venue;
import za.co.idealogic.moviemanager.repository.VenueRepository;
import za.co.idealogic.moviemanager.service.dto.VenueDTO;
import za.co.idealogic.moviemanager.service.mapper.VenueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Venue}.
 */
@Service
@Transactional
public class VenueService {

    private final Logger log = LoggerFactory.getLogger(VenueService.class);

    private final VenueRepository venueRepository;

    private final VenueMapper venueMapper;

    public VenueService(VenueRepository venueRepository, VenueMapper venueMapper) {
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    /**
     * Save a venue.
     *
     * @param venueDTO the entity to save.
     * @return the persisted entity.
     */
    public VenueDTO save(VenueDTO venueDTO) {
        log.debug("Request to save Venue : {}", venueDTO);
        Venue venue = venueMapper.toEntity(venueDTO);
        venue = venueRepository.save(venue);
        return venueMapper.toDto(venue);
    }

    /**
     * Get all the venues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<VenueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Venues");
        return venueRepository.findAll(pageable)
            .map(venueMapper::toDto);
    }


    /**
     * Get one venue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VenueDTO> findOne(Long id) {
        log.debug("Request to get Venue : {}", id);
        return venueRepository.findById(id)
            .map(venueMapper::toDto);
    }

    /**
     * Delete the venue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Venue : {}", id);
        venueRepository.deleteById(id);
    }
}
