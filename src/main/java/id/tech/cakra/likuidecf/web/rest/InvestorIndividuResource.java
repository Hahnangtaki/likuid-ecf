package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.InvestorIndividuService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.InvestorIndividuDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.InvestorIndividu}.
 */
@RestController
@RequestMapping("/api")
public class InvestorIndividuResource {

    private final Logger log = LoggerFactory.getLogger(InvestorIndividuResource.class);

    private static final String ENTITY_NAME = "investorIndividu";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvestorIndividuService investorIndividuService;

    public InvestorIndividuResource(InvestorIndividuService investorIndividuService) {
        this.investorIndividuService = investorIndividuService;
    }

    /**
     * {@code POST  /investor-individus} : Create a new investorIndividu.
     *
     * @param investorIndividuDTO the investorIndividuDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new investorIndividuDTO, or with status {@code 400 (Bad Request)} if the investorIndividu has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/investor-individus")
    public ResponseEntity<InvestorIndividuDTO> createInvestorIndividu(@Valid @RequestBody InvestorIndividuDTO investorIndividuDTO) throws URISyntaxException {
        log.debug("REST request to save InvestorIndividu : {}", investorIndividuDTO);
        if (investorIndividuDTO.getId() != null) {
            throw new BadRequestAlertException("A new investorIndividu cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvestorIndividuDTO result = investorIndividuService.save(investorIndividuDTO);
        return ResponseEntity.created(new URI("/api/investor-individus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /investor-individus} : Updates an existing investorIndividu.
     *
     * @param investorIndividuDTO the investorIndividuDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated investorIndividuDTO,
     * or with status {@code 400 (Bad Request)} if the investorIndividuDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the investorIndividuDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/investor-individus")
    public ResponseEntity<InvestorIndividuDTO> updateInvestorIndividu(@Valid @RequestBody InvestorIndividuDTO investorIndividuDTO) throws URISyntaxException {
        log.debug("REST request to update InvestorIndividu : {}", investorIndividuDTO);
        if (investorIndividuDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestorIndividuDTO result = investorIndividuService.save(investorIndividuDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, investorIndividuDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /investor-individus} : get all the investorIndividus.
     *

     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of investorIndividus in body.
     */
    @GetMapping("/investor-individus")
    public List<InvestorIndividuDTO> getAllInvestorIndividus(@RequestParam(required = false) String filter) {
        if ("investor-is-null".equals(filter)) {
            log.debug("REST request to get all InvestorIndividus where investor is null");
            return investorIndividuService.findAllWhereInvestorIsNull();
        }
        log.debug("REST request to get all InvestorIndividus");
        return investorIndividuService.findAll();
    }

    /**
     * {@code GET  /investor-individus/:id} : get the "id" investorIndividu.
     *
     * @param id the id of the investorIndividuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the investorIndividuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/investor-individus/{id}")
    public ResponseEntity<InvestorIndividuDTO> getInvestorIndividu(@PathVariable Long id) {
        log.debug("REST request to get InvestorIndividu : {}", id);
        Optional<InvestorIndividuDTO> investorIndividuDTO = investorIndividuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investorIndividuDTO);
    }

    /**
     * {@code DELETE  /investor-individus/:id} : delete the "id" investorIndividu.
     *
     * @param id the id of the investorIndividuDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/investor-individus/{id}")
    public ResponseEntity<Void> deleteInvestorIndividu(@PathVariable Long id) {
        log.debug("REST request to delete InvestorIndividu : {}", id);
        investorIndividuService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
