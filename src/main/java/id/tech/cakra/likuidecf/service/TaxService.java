package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.TaxDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.Tax}.
 */
public interface TaxService {

    /**
     * Save a tax.
     *
     * @param taxDTO the entity to save.
     * @return the persisted entity.
     */
    TaxDTO save(TaxDTO taxDTO);

    /**
     * Get all the taxes.
     *
     * @return the list of entities.
     */
    List<TaxDTO> findAll();


    /**
     * Get the "id" tax.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaxDTO> findOne(Long id);

    /**
     * Delete the "id" tax.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
