package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.FundRaiserService;
import id.tech.cakra.likuidecf.domain.FundRaiser;
import id.tech.cakra.likuidecf.repository.FundRaiserRepository;
import id.tech.cakra.likuidecf.service.dto.FundRaiserDTO;
import id.tech.cakra.likuidecf.service.mapper.FundRaiserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link FundRaiser}.
 */
@Service
@Transactional
public class FundRaiserServiceImpl implements FundRaiserService {

    private final Logger log = LoggerFactory.getLogger(FundRaiserServiceImpl.class);

    private final FundRaiserRepository fundRaiserRepository;

    private final FundRaiserMapper fundRaiserMapper;

    public FundRaiserServiceImpl(FundRaiserRepository fundRaiserRepository, FundRaiserMapper fundRaiserMapper) {
        this.fundRaiserRepository = fundRaiserRepository;
        this.fundRaiserMapper = fundRaiserMapper;
    }

    /**
     * Save a fundRaiser.
     *
     * @param fundRaiserDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FundRaiserDTO save(FundRaiserDTO fundRaiserDTO) {
        log.debug("Request to save FundRaiser : {}", fundRaiserDTO);
        FundRaiser fundRaiser = fundRaiserMapper.toEntity(fundRaiserDTO);
        fundRaiser = fundRaiserRepository.save(fundRaiser);
        return fundRaiserMapper.toDto(fundRaiser);
    }

    /**
     * Get all the fundRaisers.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FundRaiserDTO> findAll() {
        log.debug("Request to get all FundRaisers");
        return fundRaiserRepository.findAll().stream()
            .map(fundRaiserMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one fundRaiser by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FundRaiserDTO> findOne(Long id) {
        log.debug("Request to get FundRaiser : {}", id);
        return fundRaiserRepository.findById(id)
            .map(fundRaiserMapper::toDto);
    }

    /**
     * Delete the fundRaiser by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FundRaiser : {}", id);
        fundRaiserRepository.deleteById(id);
    }
}
