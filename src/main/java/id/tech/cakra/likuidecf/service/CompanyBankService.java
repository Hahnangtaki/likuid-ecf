package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.CompanyBankDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.CompanyBank}.
 */
public interface CompanyBankService {

    /**
     * Save a companyBank.
     *
     * @param companyBankDTO the entity to save.
     * @return the persisted entity.
     */
    CompanyBankDTO save(CompanyBankDTO companyBankDTO);

    /**
     * Get all the companyBanks.
     *
     * @return the list of entities.
     */
    List<CompanyBankDTO> findAll();


    /**
     * Get the "id" companyBank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CompanyBankDTO> findOne(Long id);

    /**
     * Delete the "id" companyBank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
