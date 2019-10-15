package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.CampaignDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.Campaign}.
 */
public interface CampaignService {

    /**
     * Save a campaign.
     *
     * @param campaignDTO the entity to save.
     * @return the persisted entity.
     */
    CampaignDTO save(CampaignDTO campaignDTO);

    /**
     * Get all the campaigns.
     *
     * @return the list of entities.
     */
    List<CampaignDTO> findAll();


    /**
     * Get the "id" campaign.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampaignDTO> findOne(Long id);

    /**
     * Delete the "id" campaign.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
