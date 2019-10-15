package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.InvestorIndividuService;
import id.tech.cakra.likuidecf.domain.InvestorIndividu;
import id.tech.cakra.likuidecf.repository.InvestorIndividuRepository;
import id.tech.cakra.likuidecf.service.dto.InvestorIndividuDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorIndividuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link InvestorIndividu}.
 */
@Service
@Transactional
public class InvestorIndividuServiceImpl implements InvestorIndividuService {

    private final Logger log = LoggerFactory.getLogger(InvestorIndividuServiceImpl.class);

    private final InvestorIndividuRepository investorIndividuRepository;

    private final InvestorIndividuMapper investorIndividuMapper;

    public InvestorIndividuServiceImpl(InvestorIndividuRepository investorIndividuRepository, InvestorIndividuMapper investorIndividuMapper) {
        this.investorIndividuRepository = investorIndividuRepository;
        this.investorIndividuMapper = investorIndividuMapper;
    }

    /**
     * Save a investorIndividu.
     *
     * @param investorIndividuDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvestorIndividuDTO save(InvestorIndividuDTO investorIndividuDTO) {
        log.debug("Request to save InvestorIndividu : {}", investorIndividuDTO);
        InvestorIndividu investorIndividu = investorIndividuMapper.toEntity(investorIndividuDTO);
        investorIndividu = investorIndividuRepository.save(investorIndividu);
        return investorIndividuMapper.toDto(investorIndividu);
    }

    /**
     * Get all the investorIndividus.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvestorIndividuDTO> findAll() {
        log.debug("Request to get all InvestorIndividus");
        return investorIndividuRepository.findAll().stream()
            .map(investorIndividuMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the investorIndividus where Investor is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<InvestorIndividuDTO> findAllWhereInvestorIsNull() {
        log.debug("Request to get all investorIndividus where Investor is null");
        return StreamSupport
            .stream(investorIndividuRepository.findAll().spliterator(), false)
            .filter(investorIndividu -> investorIndividu.getInvestor() == null)
            .map(investorIndividuMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one investorIndividu by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorIndividuDTO> findOne(Long id) {
        log.debug("Request to get InvestorIndividu : {}", id);
        return investorIndividuRepository.findById(id)
            .map(investorIndividuMapper::toDto);
    }

    /**
     * Delete the investorIndividu by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvestorIndividu : {}", id);
        investorIndividuRepository.deleteById(id);
    }
}
