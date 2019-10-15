package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.InvestorDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.Investor}.
 */
public interface InvestorService {

    /**
     * Save a investor.
     *
     * @param investorDTO the entity to save.
     * @return the persisted entity.
     */
    InvestorDTO save(InvestorDTO investorDTO);

    /**
     * Get all the investors.
     *
     * @return the list of entities.
     */
    List<InvestorDTO> findAll();


    /**
     * Get the "id" investor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvestorDTO> findOne(Long id);

    /**
     * Delete the "id" investor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
