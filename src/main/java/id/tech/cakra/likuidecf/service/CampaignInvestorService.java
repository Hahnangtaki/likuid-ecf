package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.CampaignInvestorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.CampaignInvestor}.
 */
public interface CampaignInvestorService {

    /**
     * Save a campaignInvestor.
     *
     * @param campaignInvestorDTO the entity to save.
     * @return the persisted entity.
     */
    CampaignInvestorDTO save(CampaignInvestorDTO campaignInvestorDTO);

    /**
     * Get all the campaignInvestors.
     *
     * @return the list of entities.
     */
    List<CampaignInvestorDTO> findAll();


    /**
     * Get the "id" campaignInvestor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampaignInvestorDTO> findOne(Long id);

    /**
     * Delete the "id" campaignInvestor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
