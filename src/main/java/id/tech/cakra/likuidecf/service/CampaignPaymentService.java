package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.CampaignPaymentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.CampaignPayment}.
 */
public interface CampaignPaymentService {

    /**
     * Save a campaignPayment.
     *
     * @param campaignPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    CampaignPaymentDTO save(CampaignPaymentDTO campaignPaymentDTO);

    /**
     * Get all the campaignPayments.
     *
     * @return the list of entities.
     */
    List<CampaignPaymentDTO> findAll();


    /**
     * Get the "id" campaignPayment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampaignPaymentDTO> findOne(Long id);

    /**
     * Delete the "id" campaignPayment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
