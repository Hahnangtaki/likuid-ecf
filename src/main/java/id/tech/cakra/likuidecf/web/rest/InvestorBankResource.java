package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.InvestorBankService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.InvestorBankDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.InvestorBank}.
 */
@RestController
@RequestMapping("/api")
public class InvestorBankResource {

    private final Logger log = LoggerFactory.getLogger(InvestorBankResource.class);

    private static final String ENTITY_NAME = "investorBank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvestorBankService investorBankService;

    public InvestorBankResource(InvestorBankService investorBankService) {
        this.investorBankService = investorBankService;
    }

    /**
     * {@code POST  /investor-banks} : Create a new investorBank.
     *
     * @param investorBankDTO the investorBankDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new investorBankDTO, or with status {@code 400 (Bad Request)} if the investorBank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/investor-banks")
    public ResponseEntity<InvestorBankDTO> createInvestorBank(@Valid @RequestBody InvestorBankDTO investorBankDTO) throws URISyntaxException {
        log.debug("REST request to save InvestorBank : {}", investorBankDTO);
        if (investorBankDTO.getId() != null) {
            throw new BadRequestAlertException("A new investorBank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorBankDTO result = investorBankService.save(investorBankDTO);
        return ResponseEntity.created(new URI("/api/investor-banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /investor-banks} : Updates an existing investorBank.
     *
     * @param investorBankDTO the investorBankDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated investorBankDTO,
     * or with status {@code 400 (Bad Request)} if the investorBankDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the investorBankDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/investor-banks")
    public ResponseEntity<InvestorBankDTO> updateInvestorBank(@Valid @RequestBody InvestorBankDTO investorBankDTO) throws URISyntaxException {
        log.debug("REST request to update InvestorBank : {}", investorBankDTO);
        if (investorBankDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorBankDTO result = investorBankService.save(investorBankDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, investorBankDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /investor-banks} : get all the investorBanks.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of investorBanks in body.
     */
    @GetMapping("/investor-banks")
    public List<InvestorBankDTO> getAllInvestorBanks() {
        log.debug("REST request to get all InvestorBanks");
        return investorBankService.findAll();
    }

    /**
     * {@code GET  /investor-banks/:id} : get the "id" investorBank.
     *
     * @param id the id of the investorBankDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the investorBankDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/investor-banks/{id}")
    public ResponseEntity<InvestorBankDTO> getInvestorBank(@PathVariable Long id) {
        log.debug("REST request to get InvestorBank : {}", id);
        Optional<InvestorBankDTO> investorBankDTO = investorBankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorBankDTO);
    }

    /**
     * {@code DELETE  /investor-banks/:id} : delete the "id" investorBank.
     *
     * @param id the id of the investorBankDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/investor-banks/{id}")
    public ResponseEntity<Void> deleteInvestorBank(@PathVariable Long id) {
        log.debug("REST request to delete InvestorBank : {}", id);
        investorBankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
