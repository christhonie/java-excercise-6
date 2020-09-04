package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Screening;
import za.co.idealogic.moviemanager.repository.CinemaRepository;
import za.co.idealogic.moviemanager.repository.MovieRepository;
import za.co.idealogic.moviemanager.repository.ScreeningRepository;
import za.co.idealogic.moviemanager.service.dto.ScreeningDTO;
import za.co.idealogic.moviemanager.service.mapper.ScreeningMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
    
    @Autowired
    MovieRepository movieRepository;
    
    @Autowired
    CinemaRepository cinemaRepository;

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
    public ScreeningDTO save(ScreeningDTO screeningDTO) throws Exception{
        log.debug("Request to save Screening : {}", screeningDTO);
        
        screeningDTO = schedule(screeningDTO.getMovieId(), screeningDTO.getCinemaId(), screeningDTO.getStartTime());
        
        Screening screening = screeningMapper.toEntity(screeningDTO);
        screening = screeningRepository.save(screening);
        
        return screeningMapper.toDto(screening);
    }

    public ScreeningDTO schedule(Long movieId, Long cinemaId, Instant startTime) throws Exception{
    	log.debug("Request to scehdule - MovieId: {} CinemaId: {} StartTime: {}", movieId, cinemaId, startTime);
    	
    	ScreeningDTO screeningDTO = new ScreeningDTO();
    	
    	Instant currrentTime = Instant.now();
    	
    	if (startTime.isAfter(currrentTime)) {
    		Duration runningTime = movieRepository.findOneWithEagerRelationships(movieId).orElse(null).getRunningTime();
        	Instant endTime = startTime.plus(runningTime.toMinutes(), ChronoUnit.MINUTES);
        	
        	screeningDTO.setMovieId(movieId);
        	screeningDTO.setCinemaId(cinemaId);
        	screeningDTO.setStartTime(startTime);
        	screeningDTO.setEndTime(endTime);
        	
        	return screeningDTO;
		}
    	else {
    		throw new Exception("The start time can not be in the past");
		}  	
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
