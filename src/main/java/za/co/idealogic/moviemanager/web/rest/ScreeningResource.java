package za.co.idealogic.moviemanager.web.rest;

import za.co.idealogic.moviemanager.service.ScreeningService;
import za.co.idealogic.moviemanager.web.rest.errors.BadRequestAlertException;
import za.co.idealogic.moviemanager.web.rest.util.HeaderUtil;
import za.co.idealogic.moviemanager.web.rest.util.PaginationUtil;
import za.co.idealogic.moviemanager.web.rest.util.ResponseUtil;
import za.co.idealogic.moviemanager.service.dto.ScreeningDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link za.co.idealogic.moviemanager.domain.Screening}.
 */
@RestController
@RequestMapping("/api")
public class ScreeningResource {

    private final Logger log = LoggerFactory.getLogger(ScreeningResource.class);

    private static final String ENTITY_NAME = "screening";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ScreeningService screeningService;

    public ScreeningResource(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    /**
     * {@code POST  /screenings} : Create a new screening.
     *
     * @param screeningDTO the screeningDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new screeningDTO, or with status {@code 400 (Bad Request)} if the screening has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/screenings")
    public ResponseEntity<ScreeningDTO> createScreening(@Valid @RequestBody ScreeningDTO screeningDTO) throws URISyntaxException {
        log.debug("REST request to save Screening : {}", screeningDTO);
        if (screeningDTO.getId() != null) {
            throw new BadRequestAlertException("A new screening cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ScreeningDTO result = screeningService.save(screeningDTO);
        return ResponseEntity.created(new URI("/api/screenings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /screenings} : Updates an existing screening.
     *
     * @param screeningDTO the screeningDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated screeningDTO,
     * or with status {@code 400 (Bad Request)} if the screeningDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the screeningDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/screenings")
    public ResponseEntity<ScreeningDTO> updateScreening(@Valid @RequestBody ScreeningDTO screeningDTO) throws URISyntaxException {
        log.debug("REST request to update Screening : {}", screeningDTO);
        if (screeningDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScreeningDTO result = screeningService.save(screeningDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, screeningDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /screenings} : get all the screenings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of screenings in body.
     */
    @GetMapping("/screenings")
    public ResponseEntity<List<ScreeningDTO>> getAllScreenings(Pageable pageable) {
        log.debug("REST request to get a page of Screenings");
        Page<ScreeningDTO> page = screeningService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /screenings/:id} : get the "id" screening.
     *
     * @param id the id of the screeningDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the screeningDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/screenings/{id}")
    public ResponseEntity<ScreeningDTO> getScreening(@PathVariable Long id) {
        log.debug("REST request to get Screening : {}", id);
        Optional<ScreeningDTO> screeningDTO = screeningService.findOne(id);
        return ResponseUtil.wrapOrNotFound(screeningDTO);
    }

    /**
     * {@code DELETE  /screenings/:id} : delete the "id" screening.
     *
     * @param id the id of the screeningDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/screenings/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable Long id) {
        log.debug("REST request to delete Screening : {}", id);
        screeningService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    
    /**
     * {@code DELETE  /screenings/:id} : delete the "id" screening.
     *
     * @param id the id of the screeningDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PostMapping("/desired/screenings")
    public ResponseEntity<List<ScreeningDTO>> getScreenings(@RequestBody ScreeningDTO desiredScreening ) {
        log.debug("REST request to get a page of Screenings");
        return ResponseEntity.ok().body(screeningService.findSuitableScreeningTime(desiredScreening));
    }  
  
}
