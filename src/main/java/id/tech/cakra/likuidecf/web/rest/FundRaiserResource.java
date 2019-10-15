package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.FundRaiserService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.FundRaiserDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.FundRaiser}.
 */
@RestController
@RequestMapping("/api")
public class FundRaiserResource {

    private final Logger log = LoggerFactory.getLogger(FundRaiserResource.class);

    private static final String ENTITY_NAME = "fundRaiser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundRaiserService fundRaiserService;

    public FundRaiserResource(FundRaiserService fundRaiserService) {
        this.fundRaiserService = fundRaiserService;
    }

    /**
     * {@code POST  /fund-raisers} : Create a new fundRaiser.
     *
     * @param fundRaiserDTO the fundRaiserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundRaiserDTO, or with status {@code 400 (Bad Request)} if the fundRaiser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fund-raisers")
    public ResponseEntity<FundRaiserDTO> createFundRaiser(@Valid @RequestBody FundRaiserDTO fundRaiserDTO) throws URISyntaxException {
        log.debug("REST request to save FundRaiser : {}", fundRaiserDTO);
        if (fundRaiserDTO.getId() != null) {
            throw new BadRequestAlertException("A new fundRaiser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FundRaiserDTO result = fundRaiserService.save(fundRaiserDTO);
        return ResponseEntity.created(new URI("/api/fund-raisers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fund-raisers} : Updates an existing fundRaiser.
     *
     * @param fundRaiserDTO the fundRaiserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundRaiserDTO,
     * or with status {@code 400 (Bad Request)} if the fundRaiserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundRaiserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fund-raisers")
    public ResponseEntity<FundRaiserDTO> updateFundRaiser(@Valid @RequestBody FundRaiserDTO fundRaiserDTO) throws URISyntaxException {
        log.debug("REST request to update FundRaiser : {}", fundRaiserDTO);
        if (fundRaiserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FundRaiserDTO result = fundRaiserService.save(fundRaiserDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fundRaiserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fund-raisers} : get all the fundRaisers.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundRaisers in body.
     */
    @GetMapping("/fund-raisers")
    public List<FundRaiserDTO> getAllFundRaisers() {
        log.debug("REST request to get all FundRaisers");
        return fundRaiserService.findAll();
    }

    /**
     * {@code GET  /fund-raisers/:id} : get the "id" fundRaiser.
     *
     * @param id the id of the fundRaiserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundRaiserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fund-raisers/{id}")
    public ResponseEntity<FundRaiserDTO> getFundRaiser(@PathVariable Long id) {
        log.debug("REST request to get FundRaiser : {}", id);
        Optional<FundRaiserDTO> fundRaiserDTO = fundRaiserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundRaiserDTO);
    }

    /**
     * {@code DELETE  /fund-raisers/:id} : delete the "id" fundRaiser.
     *
     * @param id the id of the fundRaiserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fund-raisers/{id}")
    public ResponseEntity<Void> deleteFundRaiser(@PathVariable Long id) {
        log.debug("REST request to delete FundRaiser : {}", id);
        fundRaiserService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
