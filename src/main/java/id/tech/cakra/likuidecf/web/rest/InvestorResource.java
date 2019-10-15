package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.InvestorService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.InvestorDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.Investor}.
 */
@RestController
@RequestMapping("/api")
public class InvestorResource {

    private final Logger log = LoggerFactory.getLogger(InvestorResource.class);

    private static final String ENTITY_NAME = "investor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvestorService investorService;

    public InvestorResource(InvestorService investorService) {
        this.investorService = investorService;
    }

    /**
     * {@code POST  /investors} : Create a new investor.
     *
     * @param investorDTO the investorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new investorDTO, or with status {@code 400 (Bad Request)} if the investor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/investors")
    public ResponseEntity<InvestorDTO> createInvestor(@Valid @RequestBody InvestorDTO investorDTO) throws URISyntaxException {
        log.debug("REST request to save Investor : {}", investorDTO);
        if (investorDTO.getId() != null) {
            throw new BadRequestAlertException("A new investor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorDTO result = investorService.save(investorDTO);
        return ResponseEntity.created(new URI("/api/investors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /investors} : Updates an existing investor.
     *
     * @param investorDTO the investorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated investorDTO,
     * or with status {@code 400 (Bad Request)} if the investorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the investorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/investors")
    public ResponseEntity<InvestorDTO> updateInvestor(@Valid @RequestBody InvestorDTO investorDTO) throws URISyntaxException {
        log.debug("REST request to update Investor : {}", investorDTO);
        if (investorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorDTO result = investorService.save(investorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, investorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /investors} : get all the investors.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of investors in body.
     */
    @GetMapping("/investors")
    public List<InvestorDTO> getAllInvestors() {
        log.debug("REST request to get all Investors");
        return investorService.findAll();
    }

    /**
     * {@code GET  /investors/:id} : get the "id" investor.
     *
     * @param id the id of the investorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the investorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/investors/{id}")
    public ResponseEntity<InvestorDTO> getInvestor(@PathVariable Long id) {
        log.debug("REST request to get Investor : {}", id);
        Optional<InvestorDTO> investorDTO = investorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorDTO);
    }

    /**
     * {@code DELETE  /investors/:id} : delete the "id" investor.
     *
     * @param id the id of the investorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/investors/{id}")
    public ResponseEntity<Void> deleteInvestor(@PathVariable Long id) {
        log.debug("REST request to delete Investor : {}", id);
        investorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
