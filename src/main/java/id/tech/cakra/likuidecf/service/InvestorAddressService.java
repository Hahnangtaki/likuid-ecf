package id.tech.cakra.likuidecf.service;

import id.tech.cakra.likuidecf.service.dto.InvestorAddressDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link id.tech.cakra.likuidecf.domain.InvestorAddress}.
 */
public interface InvestorAddressService {

    /**
     * Save a investorAddress.
     *
     * @param investorAddressDTO the entity to save.
     * @return the persisted entity.
     */
    InvestorAddressDTO save(InvestorAddressDTO investorAddressDTO);

    /**
     * Get all the investorAddresses.
     *
     * @return the list of entities.
     */
    List<InvestorAddressDTO> findAll();


    /**
     * Get the "id" investorAddress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvestorAddressDTO> findOne(Long id);

    /**
     * Delete the "id" investorAddress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
