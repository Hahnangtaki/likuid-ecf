package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.FundRaiserBankService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.FundRaiserBankDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.FundRaiserBank}.
 */
@RestController
@RequestMapping("/api")
public class FundRaiserBankResource {

    private final Logger log = LoggerFactory.getLogger(FundRaiserBankResource.class);

    private static final String ENTITY_NAME = "fundRaiserBank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundRaiserBankService fundRaiserBankService;

    public FundRaiserBankResource(FundRaiserBankService fundRaiserBankService) {
        this.fundRaiserBankService = fundRaiserBankService;
    }

    /**
     * {@code POST  /fund-raiser-banks} : Create a new fundRaiserBank.
     *
     * @param fundRaiserBankDTO the fundRaiserBankDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundRaiserBankDTO, or with status {@code 400 (Bad Request)} if the fundRaiserBank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fund-raiser-banks")
    public ResponseEntity<FundRaiserBankDTO> createFundRaiserBank(@Valid @RequestBody FundRaiserBankDTO fundRaiserBankDTO) throws URISyntaxException {
        log.debug("REST request to save FundRaiserBank : {}", fundRaiserBankDTO);
        if (fundRaiserBankDTO.getId() != null) {
            throw new BadRequestAlertException("A new fundRaiserBank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FundRaiserBankDTO result = fundRaiserBankService.save(fundRaiserBankDTO);
        return ResponseEntity.created(new URI("/api/fund-raiser-banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fund-raiser-banks} : Updates an existing fundRaiserBank.
     *
     * @param fundRaiserBankDTO the fundRaiserBankDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundRaiserBankDTO,
     * or with status {@code 400 (Bad Request)} if the fundRaiserBankDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundRaiserBankDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fund-raiser-banks")
    public ResponseEntity<FundRaiserBankDTO> updateFundRaiserBank(@Valid @RequestBody FundRaiserBankDTO fundRaiserBankDTO) throws URISyntaxException {
        log.debug("REST request to update FundRaiserBank : {}", fundRaiserBankDTO);
        if (fundRaiserBankDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FundRaiserBankDTO result = fundRaiserBankService.save(fundRaiserBankDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fundRaiserBankDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fund-raiser-banks} : get all the fundRaiserBanks.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundRaiserBanks in body.
     */
    @GetMapping("/fund-raiser-banks")
    public List<FundRaiserBankDTO> getAllFundRaiserBanks() {
        log.debug("REST request to get all FundRaiserBanks");
        return fundRaiserBankService.findAll();
    }

    /**
     * {@code GET  /fund-raiser-banks/:id} : get the "id" fundRaiserBank.
     *
     * @param id the id of the fundRaiserBankDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundRaiserBankDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fund-raiser-banks/{id}")
    public ResponseEntity<FundRaiserBankDTO> getFundRaiserBank(@PathVariable Long id) {
        log.debug("REST request to get FundRaiserBank : {}", id);
        Optional<FundRaiserBankDTO> fundRaiserBankDTO = fundRaiserBankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundRaiserBankDTO);
    }

    /**
     * {@code DELETE  /fund-raiser-banks/:id} : delete the "id" fundRaiserBank.
     *
     * @param id the id of the fundRaiserBankDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fund-raiser-banks/{id}")
    public ResponseEntity<Void> deleteFundRaiserBank(@PathVariable Long id) {
        log.debug("REST request to delete FundRaiserBank : {}", id);
        fundRaiserBankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
