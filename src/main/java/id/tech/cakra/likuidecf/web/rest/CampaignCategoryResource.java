package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.CampaignCategoryService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.CampaignCategoryDTO;

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
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.CampaignCategory}.
 */
@RestController
@RequestMapping("/api")
public class CampaignCategoryResource {

    private final Logger log = LoggerFactory.getLogger(CampaignCategoryResource.class);

    private static final String ENTITY_NAME = "campaignCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampaignCategoryService campaignCategoryService;

    public CampaignCategoryResource(CampaignCategoryService campaignCategoryService) {
        this.campaignCategoryService = campaignCategoryService;
    }

    /**
     * {@code POST  /campaign-categories} : Create a new campaignCategory.
     *
     * @param campaignCategoryDTO the campaignCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campaignCategoryDTO, or with status {@code 400 (Bad Request)} if the campaignCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campaign-categories")
    public ResponseEntity<CampaignCategoryDTO> createCampaignCategory(@RequestBody CampaignCategoryDTO campaignCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save CampaignCategory : {}", campaignCategoryDTO);
        if (campaignCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new campaignCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampaignCategoryDTO result = campaignCategoryService.save(campaignCategoryDTO);
        return ResponseEntity.created(new URI("/api/campaign-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campaign-categories} : Updates an existing campaignCategory.
     *
     * @param campaignCategoryDTO the campaignCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campaignCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the campaignCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campaignCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campaign-categories")
    public ResponseEntity<CampaignCategoryDTO> updateCampaignCategory(@RequestBody CampaignCategoryDTO campaignCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update CampaignCategory : {}", campaignCategoryDTO);
        if (campaignCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampaignCategoryDTO result = campaignCategoryService.save(campaignCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, campaignCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campaign-categories} : get all the campaignCategories.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campaignCategories in body.
     */
    @GetMapping("/campaign-categories")
    public List<CampaignCategoryDTO> getAllCampaignCategories() {
        log.debug("REST request to get all CampaignCategories");
        return campaignCategoryService.findAll();
    }

    /**
     * {@code GET  /campaign-categories/:id} : get the "id" campaignCategory.
     *
     * @param id the id of the campaignCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campaignCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campaign-categories/{id}")
    public ResponseEntity<CampaignCategoryDTO> getCampaignCategory(@PathVariable Long id) {
        log.debug("REST request to get CampaignCategory : {}", id);
        Optional<CampaignCategoryDTO> campaignCategoryDTO = campaignCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campaignCategoryDTO);
    }

    /**
     * {@code DELETE  /campaign-categories/:id} : delete the "id" campaignCategory.
     *
     * @param id the id of the campaignCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campaign-categories/{id}")
    public ResponseEntity<Void> deleteCampaignCategory(@PathVariable Long id) {
        log.debug("REST request to delete CampaignCategory : {}", id);
        campaignCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
