package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.CampaignCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.CampaignCategory}.
 */
public interface CampaignCategoryService {

    /**
     * Save a campaignCategory.
     *
     * @param campaignCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    CampaignCategoryDTO save(CampaignCategoryDTO campaignCategoryDTO);

    /**
     * Get all the campaignCategories.
     *
     * @return the list of entities.
     */
    List<CampaignCategoryDTO> findAll();


    /**
     * Get the "id" campaignCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampaignCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" campaignCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
