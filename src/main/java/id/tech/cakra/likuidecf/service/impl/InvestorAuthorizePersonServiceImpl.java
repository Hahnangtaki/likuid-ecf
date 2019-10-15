package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.InvestorAuthorizePersonService;
import id.tech.cakra.likuidecf.domain.InvestorAuthorizePerson;
import id.tech.cakra.likuidecf.repository.InvestorAuthorizePersonRepository;
import id.tech.cakra.likuidecf.service.dto.InvestorAuthorizePersonDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorAuthorizePersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link InvestorAuthorizePerson}.
 */
@Service
@Transactional
public class InvestorAuthorizePersonServiceImpl implements InvestorAuthorizePersonService {

    private final Logger log = LoggerFactory.getLogger(InvestorAuthorizePersonServiceImpl.class);

    private final InvestorAuthorizePersonRepository investorAuthorizePersonRepository;

    private final InvestorAuthorizePersonMapper investorAuthorizePersonMapper;

    public InvestorAuthorizePersonServiceImpl(InvestorAuthorizePersonRepository investorAuthorizePersonRepository, InvestorAuthorizePersonMapper investorAuthorizePersonMapper) {
        this.investorAuthorizePersonRepository = investorAuthorizePersonRepository;
        this.investorAuthorizePersonMapper = investorAuthorizePersonMapper;
    }

    /**
     * Save a investorAuthorizePerson.
     *
     * @param investorAuthorizePersonDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvestorAuthorizePersonDTO save(InvestorAuthorizePersonDTO investorAuthorizePersonDTO) {
        log.debug("Request to save InvestorAuthorizePerson : {}", investorAuthorizePersonDTO);
        InvestorAuthorizePerson investorAuthorizePerson = investorAuthorizePersonMapper.toEntity(investorAuthorizePersonDTO);
        investorAuthorizePerson = investorAuthorizePersonRepository.save(investorAuthorizePerson);
        return investorAuthorizePersonMapper.toDto(investorAuthorizePerson);
    }

    /**
     * Get all the investorAuthorizePeople.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<InvestorAuthorizePersonDTO> findAll() {
        log.debug("Request to get all InvestorAuthorizePeople");
        return investorAuthorizePersonRepository.findAll().stream()
            .map(investorAuthorizePersonMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one investorAuthorizePerson by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestorAuthorizePersonDTO> findOne(Long id) {
        log.debug("Request to get InvestorAuthorizePerson : {}", id);
        return investorAuthorizePersonRepository.findById(id)
            .map(investorAuthorizePersonMapper::toDto);
    }

    /**
     * Delete the investorAuthorizePerson by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvestorAuthorizePerson : {}", id);
        investorAuthorizePersonRepository.deleteById(id);
    }
}
