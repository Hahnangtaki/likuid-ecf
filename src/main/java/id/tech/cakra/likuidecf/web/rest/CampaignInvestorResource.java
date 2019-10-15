package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.CampaignInvestorService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.CampaignInvestorDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.CampaignInvestor}.
 */
@RestController
@RequestMapping("/api")
public class CampaignInvestorResource {

    private final Logger log = LoggerFactory.getLogger(CampaignInvestorResource.class);

    private static final String ENTITY_NAME = "campaignInvestor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignInvestorService campaignInvestorService;

    public CampaignInvestorResource(CampaignInvestorService campaignInvestorService) {
        this.campaignInvestorService = campaignInvestorService;
    }

    /**
     * {@code POST  /campaign-investors} : Create a new campaignInvestor.
     *
     * @param campaignInvestorDTO the campaignInvestorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignInvestorDTO, or with status {@code 400 (Bad Request)} if the campaignInvestor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaign-investors")
    public ResponseEntity<CampaignInvestorDTO> createCampaignInvestor(@RequestBody CampaignInvestorDTO campaignInvestorDTO) throws URISyntaxException {
        log.debug("REST request to save CampaignInvestor : {}", campaignInvestorDTO);
        if (campaignInvestorDTO.getId() != null) {
            throw new BadRequestAlertException("A new campaignInvestor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignInvestorDTO result = campaignInvestorService.save(campaignInvestorDTO);
        return ResponseEntity.created(new URI("/api/campaign-investors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaign-investors} : Updates an existing campaignInvestor.
     *
     * @param campaignInvestorDTO the campaignInvestorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignInvestorDTO,
     * or with status {@code 400 (Bad Request)} if the campaignInvestorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignInvestorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaign-investors")
    public ResponseEntity<CampaignInvestorDTO> updateCampaignInvestor(@RequestBody CampaignInvestorDTO campaignInvestorDTO) throws URISyntaxException {
        log.debug("REST request to update CampaignInvestor : {}", campaignInvestorDTO);
        if (campaignInvestorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignInvestorDTO result = campaignInvestorService.save(campaignInvestorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, campaignInvestorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaign-investors} : get all the campaignInvestors.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaignInvestors in body.
     */
    @GetMapping("/campaign-investors")
    public List<CampaignInvestorDTO> getAllCampaignInvestors() {
        log.debug("REST request to get all CampaignInvestors");
        return campaignInvestorService.findAll();
    }

    /**
     * {@code GET  /campaign-investors/:id} : get the "id" campaignInvestor.
     *
     * @param id the id of the campaignInvestorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignInvestorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaign-investors/{id}")
    public ResponseEntity<CampaignInvestorDTO> getCampaignInvestor(@PathVariable Long id) {
        log.debug("REST request to get CampaignInvestor : {}", id);
        Optional<CampaignInvestorDTO> campaignInvestorDTO = campaignInvestorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaignInvestorDTO);
    }

    /**
     * {@code DELETE  /campaign-investors/:id} : delete the "id" campaignInvestor.
     *
     * @param id the id of the campaignInvestorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaign-investors/{id}")
    public ResponseEntity<Void> deleteCampaignInvestor(@PathVariable Long id) {
        log.debug("REST request to delete CampaignInvestor : {}", id);
        campaignInvestorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
