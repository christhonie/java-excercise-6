package za.co.idealogic.moviemanager.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.idealogic.moviemanager.domain.Cinema;
import za.co.idealogic.moviemanager.repository.CinemaRepository;
import za.co.idealogic.moviemanager.service.dto.CinemaDTO;
import za.co.idealogic.moviemanager.service.dto.InitializeCinemaDTO;
import za.co.idealogic.moviemanager.service.dto.SeatDTO;
import za.co.idealogic.moviemanager.service.mapper.CinemaMapper;

/**
 * Service Implementation for managing {@link Cinema}.
 */
@Service
@Transactional
public class CinemaService {

    private final Logger log = LoggerFactory.getLogger(CinemaService.class);

    private final CinemaRepository cinemaRepository;
    
    private final CinemaMapper cinemaMapper;
    
    @Autowired
    private SeatService seatService;
    
    private final char[] allowedSeats = "ABCDEFGHJKLMNPRSTUVWXYZ".toCharArray();
    
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
    
    /**
     * Save a cinema.
     *
     * @param cinemaDTO the entity to save.
     * @return the persisted entity.
     */
    public CinemaDTO initialize(InitializeCinemaDTO initializeCinemaDTO) {
        log.debug("Request to initialize Cinema : {}", initializeCinemaDTO);
        CinemaDTO cinemaDTO = new CinemaDTO();
        cinemaDTO.setVenueId(initializeCinemaDTO.getVenueId());
        cinemaDTO.setName(initializeCinemaDTO.getCinemaName());
        
        Cinema cinema = cinemaMapper.toEntity(cinemaDTO);
        cinema = cinemaRepository.save(cinema);
        
        String seatChars = "";
        int overflowCount = 0;
        for (int i = 0; i < initializeCinemaDTO.getNumberOfRows(); i++)
	{
            int seatCount = i - ((allowedSeats.length) * overflowCount);
            
            if(seatCount >= allowedSeats.length) {
        	overflowCount++;
        	seatCount = 0;
        	seatChars = "" + allowedSeats[overflowCount - 1];
            }
            
	    for (int j = 0; j < initializeCinemaDTO.getLongestRowCount(); j++)
	    { 
		String currentSeat = seatChars + "" + allowedSeats[seatCount] +  + (j + 1);
		log.debug(currentSeat.toString());
		SeatDTO seatDTO = new SeatDTO();
		seatDTO.setNumber(currentSeat);
		seatDTO.setCinemaId(cinema.getId());
		seatDTO.setCinemaName(cinema.getName());
		seatDTO.setRowNumber(new Long(i + 1));
		seatDTO.setSeatNumer(new Long(j + 1));
		seatService.save(seatDTO);
	    }
	}
        return cinemaMapper.toDto(cinema);
    } 
    
}

















