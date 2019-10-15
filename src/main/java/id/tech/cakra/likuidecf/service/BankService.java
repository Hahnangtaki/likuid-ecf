package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.BankDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.Bank}.
 */
public interface BankService {

    /**
     * Save a bank.
     *
     * @param bankDTO the entity to save.
     * @return the persisted entity.
     */
    BankDTO save(BankDTO bankDTO);

    /**
     * Get all the banks.
     *
     * @return the list of entities.
     */
    List<BankDTO> findAll();


    /**
     * Get the "id" bank.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BankDTO> findOne(Long id);

    /**
     * Delete the "id" bank.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
