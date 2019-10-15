package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.CampaignTransactionService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.CampaignTransactionDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.CampaignTransaction}.
 */
@RestController
@RequestMapping("/api")
public class CampaignTransactionResource {

    private final Logger log = LoggerFactory.getLogger(CampaignTransactionResource.class);

    private static final String ENTITY_NAME = "campaignTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignTransactionService campaignTransactionService;

    public CampaignTransactionResource(CampaignTransactionService campaignTransactionService) {
        this.campaignTransactionService = campaignTransactionService;
    }

    /**
     * {@code POST  /campaign-transactions} : Create a new campaignTransaction.
     *
     * @param campaignTransactionDTO the campaignTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignTransactionDTO, or with status {@code 400 (Bad Request)} if the campaignTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaign-transactions")
    public ResponseEntity<CampaignTransactionDTO> createCampaignTransaction(@Valid @RequestBody CampaignTransactionDTO campaignTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save CampaignTransaction : {}", campaignTransactionDTO);
        if (campaignTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new campaignTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignTransactionDTO result = campaignTransactionService.save(campaignTransactionDTO);
        return ResponseEntity.created(new URI("/api/campaign-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaign-transactions} : Updates an existing campaignTransaction.
     *
     * @param campaignTransactionDTO the campaignTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the campaignTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaign-transactions")
    public ResponseEntity<CampaignTransactionDTO> updateCampaignTransaction(@Valid @RequestBody CampaignTransactionDTO campaignTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update CampaignTransaction : {}", campaignTransactionDTO);
        if (campaignTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignTransactionDTO result = campaignTransactionService.save(campaignTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, campaignTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaign-transactions} : get all the campaignTransactions.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaignTransactions in body.
     */
    @GetMapping("/campaign-transactions")
    public List<CampaignTransactionDTO> getAllCampaignTransactions() {
        log.debug("REST request to get all CampaignTransactions");
        return campaignTransactionService.findAll();
    }

    /**
     * {@code GET  /campaign-transactions/:id} : get the "id" campaignTransaction.
     *
     * @param id the id of the campaignTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaign-transactions/{id}")
    public ResponseEntity<CampaignTransactionDTO> getCampaignTransaction(@PathVariable Long id) {
        log.debug("REST request to get CampaignTransaction : {}", id);
        Optional<CampaignTransactionDTO> campaignTransactionDTO = campaignTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaignTransactionDTO);
    }

    /**
     * {@code DELETE  /campaign-transactions/:id} : delete the "id" campaignTransaction.
     *
     * @param id the id of the campaignTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaign-transactions/{id}")
    public ResponseEntity<Void> deleteCampaignTransaction(@PathVariable Long id) {
        log.debug("REST request to delete CampaignTransaction : {}", id);
        campaignTransactionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
