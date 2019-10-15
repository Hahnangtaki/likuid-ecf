package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.InvestorService;
import id.tech.cakra.likuidecf.domain.Investor;
import id.tech.cakra.likuidecf.repository.InvestorRepository;
import id.tech.cakra.likuidecf.service.dto.InvestorDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Investor}.
 */
@Service
@Transactional
public class InvestorServiceImpl implements InvestorService {

    private final Logger log = LoggerFactory.getLogger(InvestorServiceImpl.class);

    private final InvestorRepository investorRepository;

    private final InvestorMapper investorMapper;

    public InvestorServiceImpl(InvestorRepository investorRepository, InvestorMapper investorMapper) {
        this.investorRepository = investorRepository;
        this.investorMapper = investorMapper;
    }

    /**
     * Save a investor.
     *
     * @param investorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvestorDTO save(InvestorDTO investorDTO) {
        log.debug("Request to save Investor : {}", investorDTO);
        Investor investor = investorMapper.toEntity(investorDTO);
        investor = investorRepository.save(investor);
        return investorMapper.toDto(investor);
    }

    /**
     * Get all the investors.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvestorDTO> findAll() {
        log.debug("Request to get all Investors");
        return investorRepository.findAll().stream()
            .map(investorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one investor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorDTO> findOne(Long id) {
        log.debug("Request to get Investor : {}", id);
        return investorRepository.findById(id)
            .map(investorMapper::toDto);
    }

    /**
     * Delete the investor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Investor : {}", id);
        investorRepository.deleteById(id);
    }
}
