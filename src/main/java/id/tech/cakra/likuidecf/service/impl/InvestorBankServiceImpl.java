package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.InvestorBankService;
import id.tech.cakra.likuidecf.domain.InvestorBank;
import id.tech.cakra.likuidecf.repository.InvestorBankRepository;
import id.tech.cakra.likuidecf.service.dto.InvestorBankDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorBankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InvestorBank}.
 */
@Service
@Transactional
public class InvestorBankServiceImpl implements InvestorBankService {

    private final Logger log = LoggerFactory.getLogger(InvestorBankServiceImpl.class);

    private final InvestorBankRepository investorBankRepository;

    private final InvestorBankMapper investorBankMapper;

    public InvestorBankServiceImpl(InvestorBankRepository investorBankRepository, InvestorBankMapper investorBankMapper) {
        this.investorBankRepository = investorBankRepository;
        this.investorBankMapper = investorBankMapper;
    }

    /**
     * Save a investorBank.
     *
     * @param investorBankDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvestorBankDTO save(InvestorBankDTO investorBankDTO) {
        log.debug("Request to save InvestorBank : {}", investorBankDTO);
        InvestorBank investorBank = investorBankMapper.toEntity(investorBankDTO);
        investorBank = investorBankRepository.save(investorBank);
        return investorBankMapper.toDto(investorBank);
    }

    /**
     * Get all the investorBanks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvestorBankDTO> findAll() {
        log.debug("Request to get all InvestorBanks");
        return investorBankRepository.findAll().stream()
            .map(investorBankMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one investorBank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorBankDTO> findOne(Long id) {
        log.debug("Request to get InvestorBank : {}", id);
        return investorBankRepository.findById(id)
            .map(investorBankMapper::toDto);
    }

    /**
     * Delete the investorBank by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvestorBank : {}", id);
        investorBankRepository.deleteById(id);
    }
}
