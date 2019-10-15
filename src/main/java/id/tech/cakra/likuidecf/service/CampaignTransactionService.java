package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.CampaignTransactionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.CampaignTransaction}.
 */
public interface CampaignTransactionService {

    /**
     * Save a campaignTransaction.
     *
     * @param campaignTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    CampaignTransactionDTO save(CampaignTransactionDTO campaignTransactionDTO);

    /**
     * Get all the campaignTransactions.
     *
     * @return the list of entities.
     */
    List<CampaignTransactionDTO> findAll();


    /**
     * Get the "id" campaignTransaction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampaignTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" campaignTransaction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
