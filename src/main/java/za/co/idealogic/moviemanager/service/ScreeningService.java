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
import java.util.ArrayList;
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
    public ScreeningDTO save(ScreeningDTO screeningDTO) {
        log.debug("Request to save Screening : {}", screeningDTO);
        
        Screening screening = screeningMapper.toEntity(screeningDTO);
        screening = screeningRepository.save(screening);
        
        return screeningMapper.toDto(screening);
    }

    
    /**
     * Schedules the endTime for the Screening before a screening is saved
     * 
     * @param movieId
     * @param cinemaId
     * @param startTime
     * @return
     * @throws Exception
     */
    public ScreeningDTO schedule(Long movieId, Long cinemaId, Instant startTime) throws Exception{
    	log.debug("Request to scehdule - MovieId: {} CinemaId: {} StartTime: {}", movieId, cinemaId, startTime);
    	
    	ScreeningDTO screeningDTO = new ScreeningDTO();
    	
    	Instant currrentTime = Instant.now();
    	
    	if (startTime.isAfter(currrentTime)) {
    		Duration runningTime = movieRepository.findRunningTimeById(movieId);
        	Instant endTime = startTime.plus(runningTime.toMinutes(), ChronoUnit.MINUTES);
        	
        	screeningDTO.setMovieId(movieId);
        	screeningDTO.setCinemaId(cinemaId);
        	screeningDTO.setStartTime(startTime);
        	screeningDTO.setEndTime(endTime);
        	
        	return save(screeningDTO);
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
		return screeningRepository.findById(id).map(screeningMapper::toDto);
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

	/**
	 * Get screenings around selected time.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Transactional(readOnly = true)
	public List<ScreeningDTO> findSuitableScreeningTime(ScreeningDTO desiredScreening) {

		List<Screening> screenings = screeningRepository.getByCinemaName(desiredScreening.getCinemaName());
		List<ScreeningDTO> screeningsDTO = new ArrayList();
		for (Screening screening : screenings) {
			if (screening.getStartTime().isBefore(desiredScreening.getStartTime().plusSeconds(7200))
					&& screening.getStartTime().isAfter(desiredScreening.getStartTime().minusSeconds(3600))) {
				if(Instant.now().minusSeconds(900).isAfter(screening.getStartTime())) { 
				
				screeningsDTO.add(screeningMapper.toDto(screening));
				}
			}
		}
		return screeningsDTO;
	}

}
