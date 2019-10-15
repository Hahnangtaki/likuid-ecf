package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.InvestorInstitutionService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.InvestorInstitutionDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.InvestorInstitution}.
 */
@RestController
@RequestMapping("/api")
public class InvestorInstitutionResource {

    private final Logger log = LoggerFactory.getLogger(InvestorInstitutionResource.class);

    private static final String ENTITY_NAME = "investorInstitution";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvestorInstitutionService investorInstitutionService;

    public InvestorInstitutionResource(InvestorInstitutionService investorInstitutionService) {
        this.investorInstitutionService = investorInstitutionService;
    }

    /**
     * {@code POST  /investor-institutions} : Create a new investorInstitution.
     *
     * @param investorInstitutionDTO the investorInstitutionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new investorInstitutionDTO, or with status {@code 400 (Bad Request)} if the investorInstitution has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/investor-institutions")
    public ResponseEntity<InvestorInstitutionDTO> createInvestorInstitution(@Valid @RequestBody InvestorInstitutionDTO investorInstitutionDTO) throws URISyntaxException {
        log.debug("REST request to save InvestorInstitution : {}", investorInstitutionDTO);
        if (investorInstitutionDTO.getId() != null) {
            throw new BadRequestAlertException("A new investorInstitution cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorInstitutionDTO result = investorInstitutionService.save(investorInstitutionDTO);
        return ResponseEntity.created(new URI("/api/investor-institutions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /investor-institutions} : Updates an existing investorInstitution.
     *
     * @param investorInstitutionDTO the investorInstitutionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated investorInstitutionDTO,
     * or with status {@code 400 (Bad Request)} if the investorInstitutionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the investorInstitutionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/investor-institutions")
    public ResponseEntity<InvestorInstitutionDTO> updateInvestorInstitution(@Valid @RequestBody InvestorInstitutionDTO investorInstitutionDTO) throws URISyntaxException {
        log.debug("REST request to update InvestorInstitution : {}", investorInstitutionDTO);
        if (investorInstitutionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorInstitutionDTO result = investorInstitutionService.save(investorInstitutionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, investorInstitutionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /investor-institutions} : get all the investorInstitutions.
     *

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of investorInstitutions in body.
     */
    @GetMapping("/investor-institutions")
    public List<InvestorInstitutionDTO> getAllInvestorInstitutions(@RequestParam(required = false) String filter) {
        if ("investor-is-null".equals(filter)) {
            log.debug("REST request to get all InvestorInstitutions where investor is null");
            return investorInstitutionService.findAllWhereInvestorIsNull();
        }
        log.debug("REST request to get all InvestorInstitutions");
        return investorInstitutionService.findAll();
    }

    /**
     * {@code GET  /investor-institutions/:id} : get the "id" investorInstitution.
     *
     * @param id the id of the investorInstitutionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the investorInstitutionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/investor-institutions/{id}")
    public ResponseEntity<InvestorInstitutionDTO> getInvestorInstitution(@PathVariable Long id) {
        log.debug("REST request to get InvestorInstitution : {}", id);
        Optional<InvestorInstitutionDTO> investorInstitutionDTO = investorInstitutionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorInstitutionDTO);
    }

    /**
     * {@code DELETE  /investor-institutions/:id} : delete the "id" investorInstitution.
     *
     * @param id the id of the investorInstitutionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/investor-institutions/{id}")
    public ResponseEntity<Void> deleteInvestorInstitution(@PathVariable Long id) {
        log.debug("REST request to delete InvestorInstitution : {}", id);
        investorInstitutionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
