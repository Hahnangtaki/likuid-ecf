package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.FundRaiserBankDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.FundRaiserBank}.
 */
public interface FundRaiserBankService {

    /**
     * Save a fundRaiserBank.
     *
     * @param fundRaiserBankDTO the entity to save.
     * @return the persisted entity.
     */
    FundRaiserBankDTO save(FundRaiserBankDTO fundRaiserBankDTO);

    /**
     * Get all the fundRaiserBanks.
     *
     * @return the list of entities.
     */
    List<FundRaiserBankDTO> findAll();


    /**
     * Get the "id" fundRaiserBank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FundRaiserBankDTO> findOne(Long id);

    /**
     * Delete the "id" fundRaiserBank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
