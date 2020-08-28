package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Screening;
import za.co.idealogic.moviemanager.repository.ScreeningRepository;
import za.co.idealogic.moviemanager.service.dto.ScreeningDTO;
import za.co.idealogic.moviemanager.service.mapper.ScreeningMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Screening}.
 */
@Service
@Transactional
public class ScreeningService {

    private final Logger log = LoggerFactory.getLogger(ScreeningService.class);

    private final ScreeningRepository screeningRepository;

    private final ScreeningMapper screeningMapper;

    public ScreeningService(ScreeningRepository screeningRepository, ScreeningMapper screeningMapper) {
        this.screeningRepository = screeningRepository;
        this.screeningMapper = screeningMapper;
    }

    /**
     * Save a screening.
     *
     * @param screeningDTO the entity to save.
     * @return the persisted entity.
     */
    public ScreeningDTO save(ScreeningDTO screeningDTO) {
        log.debug("Request to save Screening : {}", screeningDTO);
        Screening screening = screeningMapper.toEntity(screeningDTO);
        screening = screeningRepository.save(screening);
        return screeningMapper.toDto(screening);
    }

    /**
     * Get all the screenings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ScreeningDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Screenings");
        return screeningRepository.findAll(pageable)
            .map(screeningMapper::toDto);
    }


    /**
     * Get one screening by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ScreeningDTO> findOne(Long id) {
        log.debug("Request to get Screening : {}", id);
        return screeningRepository.findById(id)
            .map(screeningMapper::toDto);
    }

    /**
     * Delete the screening by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Screening : {}", id);
        screeningRepository.deleteById(id);
    }
}
