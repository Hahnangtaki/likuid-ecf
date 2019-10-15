package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.InvestorAddressService;
import id.tech.cakra.likuidecf.domain.InvestorAddress;
import id.tech.cakra.likuidecf.repository.InvestorAddressRepository;
import id.tech.cakra.likuidecf.service.dto.InvestorAddressDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InvestorAddress}.
 */
@Service
@Transactional
public class InvestorAddressServiceImpl implements InvestorAddressService {

    private final Logger log = LoggerFactory.getLogger(InvestorAddressServiceImpl.class);

    private final InvestorAddressRepository investorAddressRepository;

    private final InvestorAddressMapper investorAddressMapper;

    public InvestorAddressServiceImpl(InvestorAddressRepository investorAddressRepository, InvestorAddressMapper investorAddressMapper) {
        this.investorAddressRepository = investorAddressRepository;
        this.investorAddressMapper = investorAddressMapper;
    }

    /**
     * Save a investorAddress.
     *
     * @param investorAddressDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvestorAddressDTO save(InvestorAddressDTO investorAddressDTO) {
        log.debug("Request to save InvestorAddress : {}", investorAddressDTO);
        InvestorAddress investorAddress = investorAddressMapper.toEntity(investorAddressDTO);
        investorAddress = investorAddressRepository.save(investorAddress);
        return investorAddressMapper.toDto(investorAddress);
    }

    /**
     * Get all the investorAddresses.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvestorAddressDTO> findAll() {
        log.debug("Request to get all InvestorAddresses");
        return investorAddressRepository.findAll().stream()
            .map(investorAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one investorAddress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorAddressDTO> findOne(Long id) {
        log.debug("Request to get InvestorAddress : {}", id);
        return investorAddressRepository.findById(id)
            .map(investorAddressMapper::toDto);
    }

    /**
     * Delete the investorAddress by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvestorAddress : {}", id);
        investorAddressRepository.deleteById(id);
    }
}
