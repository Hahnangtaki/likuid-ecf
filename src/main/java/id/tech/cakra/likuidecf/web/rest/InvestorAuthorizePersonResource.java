package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.InvestorAuthorizePersonService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.InvestorAuthorizePersonDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.InvestorAuthorizePerson}.
 */
@RestController
@RequestMapping("/api")
public class InvestorAuthorizePersonResource {

    private final Logger log = LoggerFactory.getLogger(InvestorAuthorizePersonResource.class);

    private static final String ENTITY_NAME = "investorAuthorizePerson";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvestorAuthorizePersonService investorAuthorizePersonService;

    public InvestorAuthorizePersonResource(InvestorAuthorizePersonService investorAuthorizePersonService) {
        this.investorAuthorizePersonService = investorAuthorizePersonService;
    }

    /**
     * {@code POST  /investor-authorize-people} : Create a new investorAuthorizePerson.
     *
     * @param investorAuthorizePersonDTO the investorAuthorizePersonDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new investorAuthorizePersonDTO, or with status {@code 400 (Bad Request)} if the investorAuthorizePerson has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/investor-authorize-people")
    public ResponseEntity<InvestorAuthorizePersonDTO> createInvestorAuthorizePerson(@Valid @RequestBody InvestorAuthorizePersonDTO investorAuthorizePersonDTO) throws URISyntaxException {
        log.debug("REST request to save InvestorAuthorizePerson : {}", investorAuthorizePersonDTO);
        if (investorAuthorizePersonDTO.getId() != null) {
            throw new BadRequestAlertException("A new investorAuthorizePerson cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorAuthorizePersonDTO result = investorAuthorizePersonService.save(investorAuthorizePersonDTO);
        return ResponseEntity.created(new URI("/api/investor-authorize-people/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /investor-authorize-people} : Updates an existing investorAuthorizePerson.
     *
     * @param investorAuthorizePersonDTO the investorAuthorizePersonDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated investorAuthorizePersonDTO,
     * or with status {@code 400 (Bad Request)} if the investorAuthorizePersonDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the investorAuthorizePersonDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/investor-authorize-people")
    public ResponseEntity<InvestorAuthorizePersonDTO> updateInvestorAuthorizePerson(@Valid @RequestBody InvestorAuthorizePersonDTO investorAuthorizePersonDTO) throws URISyntaxException {
        log.debug("REST request to update InvestorAuthorizePerson : {}", investorAuthorizePersonDTO);
        if (investorAuthorizePersonDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorAuthorizePersonDTO result = investorAuthorizePersonService.save(investorAuthorizePersonDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, investorAuthorizePersonDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /investor-authorize-people} : get all the investorAuthorizePeople.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of investorAuthorizePeople in body.
     */
    @GetMapping("/investor-authorize-people")
    public List<InvestorAuthorizePersonDTO> getAllInvestorAuthorizePeople() {
        log.debug("REST request to get all InvestorAuthorizePeople");
        return investorAuthorizePersonService.findAll();
    }

    /**
     * {@code GET  /investor-authorize-people/:id} : get the "id" investorAuthorizePerson.
     *
     * @param id the id of the investorAuthorizePersonDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the investorAuthorizePersonDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/investor-authorize-people/{id}")
    public ResponseEntity<InvestorAuthorizePersonDTO> getInvestorAuthorizePerson(@PathVariable Long id) {
        log.debug("REST request to get InvestorAuthorizePerson : {}", id);
        Optional<InvestorAuthorizePersonDTO> investorAuthorizePersonDTO = investorAuthorizePersonService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorAuthorizePersonDTO);
    }

    /**
     * {@code DELETE  /investor-authorize-people/:id} : delete the "id" investorAuthorizePerson.
     *
     * @param id the id of the investorAuthorizePersonDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/investor-authorize-people/{id}")
    public ResponseEntity<Void> deleteInvestorAuthorizePerson(@PathVariable Long id) {
        log.debug("REST request to delete InvestorAuthorizePerson : {}", id);
        investorAuthorizePersonService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
