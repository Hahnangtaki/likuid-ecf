package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.MemberAccountDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.MemberAccount}.
 */
public interface MemberAccountService {

    /**
     * Save a memberAccount.
     *
     * @param memberAccountDTO the entity to save.
     * @return the persisted entity.
     */
    MemberAccountDTO save(MemberAccountDTO memberAccountDTO);

    /**
     * Get all the memberAccounts.
     *
     * @return the list of entities.
     */
    List<MemberAccountDTO> findAll();


    /**
     * Get the "id" memberAccount.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MemberAccountDTO> findOne(Long id);

    /**
     * Delete the "id" memberAccount.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
