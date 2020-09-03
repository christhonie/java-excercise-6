package za.co.idealogic.moviemanager.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import za.co.idealogic.moviemanager.service.CinemaService;
import za.co.idealogic.moviemanager.service.dto.CinemaDTO;
import za.co.idealogic.moviemanager.service.dto.InitializeCinemaDTO;
import za.co.idealogic.moviemanager.web.rest.errors.BadRequestAlertException;
import za.co.idealogic.moviemanager.web.rest.util.HeaderUtil;
import za.co.idealogic.moviemanager.web.rest.util.PaginationUtil;
import za.co.idealogic.moviemanager.web.rest.util.ResponseUtil;

/**
 * REST controller for managing {@link za.co.idealogic.moviemanager.domain.Cinema}.
 */
@RestController
@RequestMapping("/api")
public class CinemaResource {

    private final Logger log = LoggerFactory.getLogger(CinemaResource.class);

    private static final String ENTITY_NAME = "cinema";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CinemaService cinemaService;

    public CinemaResource(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    /**
     * {@code POST  /cinemas} : Create a new cinema.
     *
     * @param cinemaDTO the cinemaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cinemaDTO, or with status {@code 400 (Bad Request)} if the cinema has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cinemas")
    public ResponseEntity<CinemaDTO> createCinema(@Valid @RequestBody CinemaDTO cinemaDTO) throws URISyntaxException {
        log.debug("REST request to save Cinema : {}", cinemaDTO);
        if (cinemaDTO.getId() != null) {
            throw new BadRequestAlertException("A new cinema cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CinemaDTO result = cinemaService.save(cinemaDTO);
        return ResponseEntity.created(new URI("/api/cinemas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cinemas} : Updates an existing cinema.
     *
     * @param cinemaDTO the cinemaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cinemaDTO,
     * or with status {@code 400 (Bad Request)} if the cinemaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cinemaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cinemas")
    public ResponseEntity<CinemaDTO> updateCinema(@Valid @RequestBody CinemaDTO cinemaDTO) throws URISyntaxException {
        log.debug("REST request to update Cinema : {}", cinemaDTO);
        if (cinemaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CinemaDTO result = cinemaService.save(cinemaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cinemaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cinemas} : get all the cinemas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cinemas in body.
     */
    @GetMapping("/cinemas")
    public ResponseEntity<List<CinemaDTO>> getAllCinemas(Pageable pageable) {
        log.debug("REST request to get a page of Cinemas");
        Page<CinemaDTO> page = cinemaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cinemas/:id} : get the "id" cinema.
     *
     * @param id the id of the cinemaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cinemaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cinemas/{id}")
    public ResponseEntity<CinemaDTO> getCinema(@PathVariable Long id) {
        log.debug("REST request to get Cinema : {}", id);
        Optional<CinemaDTO> cinemaDTO = cinemaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cinemaDTO);
    }

    /**
     * {@code DELETE  /cinemas/:id} : delete the "id" cinema.
     *
     * @param id the id of the cinemaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cinemas/{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable Long id) {
        log.debug("REST request to delete Cinema : {}", id);
        cinemaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * {@code POST  /cinemas} : Create a new cinema.
     *
     * @param cinemaDTO the cinemaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cinemaDTO, or with status {@code 400 (Bad Request)} if the cinema has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cinemas/initialize")
    public ResponseEntity<CinemaDTO> initializeCinema(@Valid @RequestBody InitializeCinemaDTO initializeCinemaDTO) throws URISyntaxException {
        log.debug("REST request to save Cinema : {}", initializeCinemaDTO);

        CinemaDTO result = cinemaService.initialize(initializeCinemaDTO);
        return ResponseEntity.created(new URI("/api/cinemas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}


















