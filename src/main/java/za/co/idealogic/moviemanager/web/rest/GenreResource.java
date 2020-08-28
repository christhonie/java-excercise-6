package za.co.idealogic.moviemanager.web.rest;

import za.co.idealogic.moviemanager.service.GenreService;
import za.co.idealogic.moviemanager.web.rest.errors.BadRequestAlertException;
import za.co.idealogic.moviemanager.web.rest.util.HeaderUtil;
import za.co.idealogic.moviemanager.web.rest.util.PaginationUtil;
import za.co.idealogic.moviemanager.web.rest.util.ResponseUtil;
import za.co.idealogic.moviemanager.service.dto.GenreDTO;

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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link za.co.idealogic.moviemanager.domain.Genre}.
 */
@RestController
@RequestMapping("/api")
public class GenreResource {

    private final Logger log = LoggerFactory.getLogger(GenreResource.class);

    private static final String ENTITY_NAME = "genre";

    @Value("${spring.application.name}")
    private String applicationName;

    private final GenreService genreService;

    public GenreResource(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * {@code POST  /genres} : Create a new genre.
     *
     * @param genreDTO the genreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new genreDTO, or with status {@code 400 (Bad Request)} if the genre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/genres")
    public ResponseEntity<GenreDTO> createGenre(@Valid @RequestBody GenreDTO genreDTO) throws URISyntaxException {
        log.debug("REST request to save Genre : {}", genreDTO);
        if (genreDTO.getId() != null) {
            throw new BadRequestAlertException("A new genre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GenreDTO result = genreService.save(genreDTO);
        return ResponseEntity.created(new URI("/api/genres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /genres} : Updates an existing genre.
     *
     * @param genreDTO the genreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated genreDTO,
     * or with status {@code 400 (Bad Request)} if the genreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the genreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/genres")
    public ResponseEntity<GenreDTO> updateGenre(@Valid @RequestBody GenreDTO genreDTO) throws URISyntaxException {
        log.debug("REST request to update Genre : {}", genreDTO);
        if (genreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GenreDTO result = genreService.save(genreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, genreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /genres} : get all the genres.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of genres in body.
     */
    @GetMapping("/genres")
    public ResponseEntity<List<GenreDTO>> getAllGenres(Pageable pageable) {
        log.debug("REST request to get a page of Genres");
        Page<GenreDTO> page = genreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /genres/:id} : get the "id" genre.
     *
     * @param id the id of the genreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the genreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/genres/{id}")
    public ResponseEntity<GenreDTO> getGenre(@PathVariable Long id) {
        log.debug("REST request to get Genre : {}", id);
        Optional<GenreDTO> genreDTO = genreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(genreDTO);
    }

    /**
     * {@code DELETE  /genres/:id} : delete the "id" genre.
     *
     * @param id the id of the genreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/genres/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        log.debug("REST request to delete Genre : {}", id);
        genreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
