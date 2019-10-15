package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.InvestorIndividuDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.InvestorIndividu}.
 */
public interface InvestorIndividuService {

    /**
     * Save a investorIndividu.
     *
     * @param investorIndividuDTO the entity to save.
     * @return the persisted entity.
     */
    InvestorIndividuDTO save(InvestorIndividuDTO investorIndividuDTO);

    /**
     * Get all the investorIndividus.
     *
     * @return the list of entities.
     */
    List<InvestorIndividuDTO> findAll();
    /**
     * Get all the InvestorIndividuDTO where Investor is {@code null}.
     *
     * @return the list of entities.
     */
    List<InvestorIndividuDTO> findAllWhereInvestorIsNull();


    /**
     * Get the "id" investorIndividu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvestorIndividuDTO> findOne(Long id);

    /**
     * Delete the "id" investorIndividu.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
