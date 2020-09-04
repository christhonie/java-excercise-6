package za.co.idealogic.moviemanager.service;

import za.co.idealogic.moviemanager.domain.Movie;
import za.co.idealogic.moviemanager.repository.MovieRepository;
import za.co.idealogic.moviemanager.service.dto.MovieDTO;
import za.co.idealogic.moviemanager.service.mapper.MovieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Movie}.
 */
@Service
@Transactional
public class MovieService {

    private final Logger log = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    public MovieService(MovieRepository movieRepository, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
    }

    /**
     * Save a movie.
     *
     * @param movieDTO the entity to save.
     * @return the persisted entity.
     */
    public MovieDTO save(MovieDTO movieDTO) {
        log.debug("Request to save Movie : {}", movieDTO);
        Movie movie = movieMapper.toEntity(movieDTO);
        movie = movieRepository.save(movie);
        return movieMapper.toDto(movie);
    }

    /**
     * Get all the movies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MovieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Movies");
        return movieRepository.findAll(pageable)
            .map(movieMapper::toDto);
    }


    /**
     * Get all the movies with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MovieDTO> findAllWithEagerRelationships(Pageable pageable) {
        return movieRepository.findAllWithEagerRelationships(pageable).map(movieMapper::toDto);
}

    /**
     * Get one movie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MovieDTO> findOne(Long id) {
        log.debug("Request to get Movie : {}", id);
        return movieRepository.findOneWithEagerRelationships(id)
            .map(movieMapper::toDto);



        /*
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^
 *      Alternative (long and legacy) way to code the above map function
 *      ----------------------------------------------------------------
 *
 *      Movie movie = movieRepository.findOneWithEagerRelationships(id).orElse(null);
 *      MovieDTO movieDTO = movieMapper.toDto(movie);
 *      return Optional.of(movieDTO);
 */
    }


    @Transactional(readOnly = true)
    public List<MovieDTO> findAll(int year, String sort) {
        List<Movie> movie;

        switch (sort.toLowerCase()) {

            case "asc":
                log.debug("Request to get Movie : {}", year);
                movie = movieRepository.findMovieByYearOrderByNameAsc(year);
                return movieMapper.toDto(movie);

            case "desc":
                log.debug("Request to get Movie : {}", year);
                movie = movieRepository.findMovieByYearOrderByNameDesc(year);
                return movieMapper.toDto(movie);

            default:
                log.debug("Request to get Movie : {}", year);
                movie = movieRepository.findMovieByYearOrderByNameAsc(year);
                return movieMapper.toDto(movie);
        }
    }



    /**
     * Delete the movie by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Movie : {}", id);
        movieRepository.deleteById(id);
    }
}
