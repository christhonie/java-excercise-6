package za.co.idealogic.moviemanager.web.rest;

import za.co.idealogic.moviemanager.domain.Genre;
import za.co.idealogic.moviemanager.domain.Movie;
import za.co.idealogic.moviemanager.service.MovieService;
import za.co.idealogic.moviemanager.web.rest.errors.BadRequestAlertException;
import za.co.idealogic.moviemanager.web.rest.util.HeaderUtil;
import za.co.idealogic.moviemanager.web.rest.util.PaginationUtil;
import za.co.idealogic.moviemanager.web.rest.util.ResponseUtil;
import za.co.idealogic.moviemanager.service.dto.MovieDTO;

import org.h2.command.dml.SelectOrderBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link za.co.idealogic.moviemanager.domain.Movie}.
 */
@RestController
@RequestMapping("/api")
public class MovieResource {

    private final Logger log = LoggerFactory.getLogger(MovieResource.class);

    private static final String ENTITY_NAME = "movie";

    @Value("${spring.application.name}")
    private String applicationName;

    private final MovieService movieService;

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * {@code POST  /movies} : Create a new movie.
     *
     * @param movieDTO the movieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new movieDTO, or with status {@code 400 (Bad Request)} if the movie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/movies")
    public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) throws URISyntaxException {
        log.debug("REST request to save Movie : {}", movieDTO);
        if (movieDTO.getId() != null) {
            throw new BadRequestAlertException("A new movie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MovieDTO result = movieService.save(movieDTO);
        return ResponseEntity.created(new URI("/api/movies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /movies} : Updates an existing movie.
     *
     * @param movieDTO the movieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated movieDTO,
     * or with status {@code 400 (Bad Request)} if the movieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the movieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/movies")
    public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO movieDTO) throws URISyntaxException {
        log.debug("REST request to update Movie : {}", movieDTO);
        if (movieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MovieDTO result = movieService.save(movieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, movieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /movies} : get all the movies.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of movies in body.
     */
    @GetMapping("/movies")
    public ResponseEntity<List<MovieDTO>> getAllMovies(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Movies");
        Page<MovieDTO> page;
        if (eagerload) {
            page = movieService.findAllWithEagerRelationships(pageable);
        } else {
            page = movieService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /movies/:id} : get the "id" movie.
     *
     * @param id the id of the movieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the movieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        log.debug("REST request to get Movie : {}", id);
        Optional<MovieDTO> movieDTO = movieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(movieDTO);
    }

    @GetMapping("/movies/getName/{partial}")
    public ResponseEntity<List<MovieDTO>> getMovie(@PathVariable String partial) {
        log.debug("REST request to get Movie : {}", partial);
        List<MovieDTO> movieDTO = movieService.findAllByName(partial);
        return ResponseEntity.ok().body(movieDTO);
    }
    
    @GetMapping("/movies/getName/{partial}/{sort}")
    public ResponseEntity<List<MovieDTO>> getMovie(@PathVariable String partial, @PathVariable String sort) {
        log.debug("REST request to get Movie : {}", partial);
        List<MovieDTO> movieDTO = movieService.findAllByName(partial, sort);
        return ResponseEntity.ok().body(movieDTO);
    }

    /**
     * getAllMoviesByDuration will return a range of movies by a filtered runningTime.
     * @param greaterThan this value is the lower limit of runningTime, it is optional and is null by default.
     * @param lessThan this value is the upper limit of runningTime, it is optional and is null by default.
     * @return
     */
   
    @GetMapping("/movies/duration")
    public ResponseEntity<List<MovieDTO>> getAllMoviesByDuration(@RequestParam(required = false) Duration greaterThan, @RequestParam(required = false) Duration lessThan ) {
        log.debug("REST request to get a page of Movies");
        List<MovieDTO> movieDTO = null;
		try {
			movieDTO = movieService.findMoviesByDuration(greaterThan, lessThan);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
        return ResponseEntity.ok().body(movieDTO);
    }

    /**
     * Search for a movie by year
     * @param year that we are searching for
     * @param sortAsc true if sorting ascending, false if descending 
     * @return a list of movies matching the criteria
     */
    @GetMapping("/movies/getYear/{year}")
    public ResponseEntity<List<MovieDTO>> getMovieAsc(@PathVariable Long year,@RequestParam(required = false) Boolean sortAsc) {
        log.debug("REST request to get Movie : {}", year);
        List<MovieDTO> movieDTO = movieService.findAll(year, sortAsc);
        return ResponseEntity.ok().body(movieDTO);
    }

    /**
     * {@code DELETE  /movies/:id} : delete the "id" movie.
     *
     * @param id the id of the movieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        log.debug("REST request to delete Movie : {}", id);
        movieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
    @GetMapping("/movies/genre/{genre}")
    public ResponseEntity<List<MovieDTO>> findByGenre(@PathVariable String genre, @RequestParam(required = false) Boolean sortDesc) {
        log.debug("REST request to get Movies by : {}", genre);
        List<MovieDTO> movieDTO = movieService.findByGenre(genre, sortDesc);
        return ResponseEntity.ok().body(movieDTO);
       }
        
}
