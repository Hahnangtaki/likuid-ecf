package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.InvestorInstitutionService;
import id.tech.cakra.likuidecf.domain.InvestorInstitution;
import id.tech.cakra.likuidecf.repository.InvestorInstitutionRepository;
import id.tech.cakra.likuidecf.service.dto.InvestorInstitutionDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorInstitutionMapper;
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
 * Service Implementation for managing {@link InvestorInstitution}.
 */
@Service
@Transactional
public class InvestorInstitutionServiceImpl implements InvestorInstitutionService {

    private final Logger log = LoggerFactory.getLogger(InvestorInstitutionServiceImpl.class);

    private final InvestorInstitutionRepository investorInstitutionRepository;

    private final InvestorInstitutionMapper investorInstitutionMapper;

    public InvestorInstitutionServiceImpl(InvestorInstitutionRepository investorInstitutionRepository, InvestorInstitutionMapper investorInstitutionMapper) {
        this.investorInstitutionRepository = investorInstitutionRepository;
        this.investorInstitutionMapper = investorInstitutionMapper;
    }

    /**
     * Save a investorInstitution.
     *
     * @param investorInstitutionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvestorInstitutionDTO save(InvestorInstitutionDTO investorInstitutionDTO) {
        log.debug("Request to save InvestorInstitution : {}", investorInstitutionDTO);
        InvestorInstitution investorInstitution = investorInstitutionMapper.toEntity(investorInstitutionDTO);
        investorInstitution = investorInstitutionRepository.save(investorInstitution);
        return investorInstitutionMapper.toDto(investorInstitution);
    }

    /**
     * Get all the investorInstitutions.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvestorInstitutionDTO> findAll() {
        log.debug("Request to get all InvestorInstitutions");
        return investorInstitutionRepository.findAll().stream()
            .map(investorInstitutionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
    *  Get all the investorInstitutions where Investor is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<InvestorInstitutionDTO> findAllWhereInvestorIsNull() {
        log.debug("Request to get all investorInstitutions where Investor is null");
        return StreamSupport
            .stream(investorInstitutionRepository.findAll().spliterator(), false)
            .filter(investorInstitution -> investorInstitution.getInvestor() == null)
            .map(investorInstitutionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one investorInstitution by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorInstitutionDTO> findOne(Long id) {
        log.debug("Request to get InvestorInstitution : {}", id);
        return investorInstitutionRepository.findById(id)
            .map(investorInstitutionMapper::toDto);
    }

    /**
     * Delete the investorInstitution by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvestorInstitution : {}", id);
        investorInstitutionRepository.deleteById(id);
    }
}
