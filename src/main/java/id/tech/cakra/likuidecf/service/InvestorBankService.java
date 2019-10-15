package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.InvestorBankDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.InvestorBank}.
 */
public interface InvestorBankService {

    /**
     * Save a investorBank.
     *
     * @param investorBankDTO the entity to save.
     * @return the persisted entity.
     */
    InvestorBankDTO save(InvestorBankDTO investorBankDTO);

    /**
     * Get all the investorBanks.
     *
     * @return the list of entities.
     */
    List<InvestorBankDTO> findAll();


    /**
     * Get the "id" investorBank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvestorBankDTO> findOne(Long id);

    /**
     * Delete the "id" investorBank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
