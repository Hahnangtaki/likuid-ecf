package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.InvestorInstitutionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.InvestorInstitution}.
 */
public interface InvestorInstitutionService {

    /**
     * Save a investorInstitution.
     *
     * @param investorInstitutionDTO the entity to save.
     * @return the persisted entity.
     */
    InvestorInstitutionDTO save(InvestorInstitutionDTO investorInstitutionDTO);

    /**
     * Get all the investorInstitutions.
     *
     * @return the list of entities.
     */
    List<InvestorInstitutionDTO> findAll();
    /**
     * Get all the InvestorInstitutionDTO where Investor is {@code null}.
     *
     * @return the list of entities.
     */
    List<InvestorInstitutionDTO> findAllWhereInvestorIsNull();


    /**
     * Get the "id" investorInstitution.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvestorInstitutionDTO> findOne(Long id);

    /**
     * Delete the "id" investorInstitution.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
