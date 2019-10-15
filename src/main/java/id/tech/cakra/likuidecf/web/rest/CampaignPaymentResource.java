package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.CampaignPaymentService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.CampaignPaymentDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.CampaignPayment}.
 */
@RestController
@RequestMapping("/api")
public class CampaignPaymentResource {

    private final Logger log = LoggerFactory.getLogger(CampaignPaymentResource.class);

    private static final String ENTITY_NAME = "campaignPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignPaymentService campaignPaymentService;

    public CampaignPaymentResource(CampaignPaymentService campaignPaymentService) {
        this.campaignPaymentService = campaignPaymentService;
    }

    /**
     * {@code POST  /campaign-payments} : Create a new campaignPayment.
     *
     * @param campaignPaymentDTO the campaignPaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignPaymentDTO, or with status {@code 400 (Bad Request)} if the campaignPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaign-payments")
    public ResponseEntity<CampaignPaymentDTO> createCampaignPayment(@Valid @RequestBody CampaignPaymentDTO campaignPaymentDTO) throws URISyntaxException {
        log.debug("REST request to save CampaignPayment : {}", campaignPaymentDTO);
        if (campaignPaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new campaignPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignPaymentDTO result = campaignPaymentService.save(campaignPaymentDTO);
        return ResponseEntity.created(new URI("/api/campaign-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaign-payments} : Updates an existing campaignPayment.
     *
     * @param campaignPaymentDTO the campaignPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the campaignPaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaign-payments")
    public ResponseEntity<CampaignPaymentDTO> updateCampaignPayment(@Valid @RequestBody CampaignPaymentDTO campaignPaymentDTO) throws URISyntaxException {
        log.debug("REST request to update CampaignPayment : {}", campaignPaymentDTO);
        if (campaignPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignPaymentDTO result = campaignPaymentService.save(campaignPaymentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, campaignPaymentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaign-payments} : get all the campaignPayments.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaignPayments in body.
     */
    @GetMapping("/campaign-payments")
    public List<CampaignPaymentDTO> getAllCampaignPayments() {
        log.debug("REST request to get all CampaignPayments");
        return campaignPaymentService.findAll();
    }

    /**
     * {@code GET  /campaign-payments/:id} : get the "id" campaignPayment.
     *
     * @param id the id of the campaignPaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignPaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaign-payments/{id}")
    public ResponseEntity<CampaignPaymentDTO> getCampaignPayment(@PathVariable Long id) {
        log.debug("REST request to get CampaignPayment : {}", id);
        Optional<CampaignPaymentDTO> campaignPaymentDTO = campaignPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaignPaymentDTO);
    }

    /**
     * {@code DELETE  /campaign-payments/:id} : delete the "id" campaignPayment.
     *
     * @param id the id of the campaignPaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaign-payments/{id}")
    public ResponseEntity<Void> deleteCampaignPayment(@PathVariable Long id) {
        log.debug("REST request to delete CampaignPayment : {}", id);
        campaignPaymentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
