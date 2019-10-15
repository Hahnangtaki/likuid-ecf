package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.InvestorAddressService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.InvestorAddressDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.InvestorAddress}.
 */
@RestController
@RequestMapping("/api")
public class InvestorAddressResource {

    private final Logger log = LoggerFactory.getLogger(InvestorAddressResource.class);

    private static final String ENTITY_NAME = "investorAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvestorAddressService investorAddressService;

    public InvestorAddressResource(InvestorAddressService investorAddressService) {
        this.investorAddressService = investorAddressService;
    }

    /**
     * {@code POST  /investor-addresses} : Create a new investorAddress.
     *
     * @param investorAddressDTO the investorAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new investorAddressDTO, or with status {@code 400 (Bad Request)} if the investorAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/investor-addresses")
    public ResponseEntity<InvestorAddressDTO> createInvestorAddress(@Valid @RequestBody InvestorAddressDTO investorAddressDTO) throws URISyntaxException {
        log.debug("REST request to save InvestorAddress : {}", investorAddressDTO);
        if (investorAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new investorAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorAddressDTO result = investorAddressService.save(investorAddressDTO);
        return ResponseEntity.created(new URI("/api/investor-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /investor-addresses} : Updates an existing investorAddress.
     *
     * @param investorAddressDTO the investorAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated investorAddressDTO,
     * or with status {@code 400 (Bad Request)} if the investorAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the investorAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/investor-addresses")
    public ResponseEntity<InvestorAddressDTO> updateInvestorAddress(@Valid @RequestBody InvestorAddressDTO investorAddressDTO) throws URISyntaxException {
        log.debug("REST request to update InvestorAddress : {}", investorAddressDTO);
        if (investorAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorAddressDTO result = investorAddressService.save(investorAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, investorAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /investor-addresses} : get all the investorAddresses.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of investorAddresses in body.
     */
    @GetMapping("/investor-addresses")
    public List<InvestorAddressDTO> getAllInvestorAddresses() {
        log.debug("REST request to get all InvestorAddresses");
        return investorAddressService.findAll();
    }

    /**
     * {@code GET  /investor-addresses/:id} : get the "id" investorAddress.
     *
     * @param id the id of the investorAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the investorAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/investor-addresses/{id}")
    public ResponseEntity<InvestorAddressDTO> getInvestorAddress(@PathVariable Long id) {
        log.debug("REST request to get InvestorAddress : {}", id);
        Optional<InvestorAddressDTO> investorAddressDTO = investorAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorAddressDTO);
    }

    /**
     * {@code DELETE  /investor-addresses/:id} : delete the "id" investorAddress.
     *
     * @param id the id of the investorAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/investor-addresses/{id}")
    public ResponseEntity<Void> deleteInvestorAddress(@PathVariable Long id) {
        log.debug("REST request to delete InvestorAddress : {}", id);
        investorAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
