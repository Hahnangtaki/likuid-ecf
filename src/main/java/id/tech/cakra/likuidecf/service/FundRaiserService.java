package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.FundRaiserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.FundRaiser}.
 */
public interface FundRaiserService {

    /**
     * Save a fundRaiser.
     *
     * @param fundRaiserDTO the entity to save.
     * @return the persisted entity.
     */
    FundRaiserDTO save(FundRaiserDTO fundRaiserDTO);

    /**
     * Get all the fundRaisers.
     *
     * @return the list of entities.
     */
    List<FundRaiserDTO> findAll();


    /**
     * Get the "id" fundRaiser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FundRaiserDTO> findOne(Long id);

    /**
     * Delete the "id" fundRaiser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
