package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.FundRaiserBankService;
import id.tech.cakra.likuidecf.domain.FundRaiserBank;
import id.tech.cakra.likuidecf.repository.FundRaiserBankRepository;
import id.tech.cakra.likuidecf.service.dto.FundRaiserBankDTO;
import id.tech.cakra.likuidecf.service.mapper.FundRaiserBankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FundRaiserBank}.
 */
@Service
@Transactional
public class FundRaiserBankServiceImpl implements FundRaiserBankService {

    private final Logger log = LoggerFactory.getLogger(FundRaiserBankServiceImpl.class);

    private final FundRaiserBankRepository fundRaiserBankRepository;

    private final FundRaiserBankMapper fundRaiserBankMapper;

    public FundRaiserBankServiceImpl(FundRaiserBankRepository fundRaiserBankRepository, FundRaiserBankMapper fundRaiserBankMapper) {
        this.fundRaiserBankRepository = fundRaiserBankRepository;
        this.fundRaiserBankMapper = fundRaiserBankMapper;
    }

    /**
     * Save a fundRaiserBank.
     *
     * @param fundRaiserBankDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FundRaiserBankDTO save(FundRaiserBankDTO fundRaiserBankDTO) {
        log.debug("Request to save FundRaiserBank : {}", fundRaiserBankDTO);
        FundRaiserBank fundRaiserBank = fundRaiserBankMapper.toEntity(fundRaiserBankDTO);
        fundRaiserBank = fundRaiserBankRepository.save(fundRaiserBank);
        return fundRaiserBankMapper.toDto(fundRaiserBank);
    }

    /**
     * Get all the fundRaiserBanks.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FundRaiserBankDTO> findAll() {
        log.debug("Request to get all FundRaiserBanks");
        return fundRaiserBankRepository.findAll().stream()
            .map(fundRaiserBankMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one fundRaiserBank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FundRaiserBankDTO> findOne(Long id) {
        log.debug("Request to get FundRaiserBank : {}", id);
        return fundRaiserBankRepository.findById(id)
            .map(fundRaiserBankMapper::toDto);
    }

    /**
     * Delete the fundRaiserBank by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FundRaiserBank : {}", id);
        fundRaiserBankRepository.deleteById(id);
    }
}
