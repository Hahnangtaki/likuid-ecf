package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.InvestorAuthorizePersonDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.InvestorAuthorizePerson}.
 */
public interface InvestorAuthorizePersonService {

    /**
     * Save a investorAuthorizePerson.
     *
     * @param investorAuthorizePersonDTO the entity to save.
     * @return the persisted entity.
     */
    InvestorAuthorizePersonDTO save(InvestorAuthorizePersonDTO investorAuthorizePersonDTO);

    /**
     * Get all the investorAuthorizePeople.
     *
     * @return the list of entities.
     */
    List<InvestorAuthorizePersonDTO> findAll();


    /**
     * Get the "id" investorAuthorizePerson.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvestorAuthorizePersonDTO> findOne(Long id);

    /**
     * Delete the "id" investorAuthorizePerson.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
