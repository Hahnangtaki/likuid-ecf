package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.CampaignTransactionService;
import id.tech.cakra.likuidecf.domain.CampaignTransaction;
import id.tech.cakra.likuidecf.repository.CampaignTransactionRepository;
import id.tech.cakra.likuidecf.service.dto.CampaignTransactionDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CampaignTransaction}.
 */
@Service
@Transactional
public class CampaignTransactionServiceImpl implements CampaignTransactionService {

    private final Logger log = LoggerFactory.getLogger(CampaignTransactionServiceImpl.class);

    private final CampaignTransactionRepository campaignTransactionRepository;

    private final CampaignTransactionMapper campaignTransactionMapper;

    public CampaignTransactionServiceImpl(CampaignTransactionRepository campaignTransactionRepository, CampaignTransactionMapper campaignTransactionMapper) {
        this.campaignTransactionRepository = campaignTransactionRepository;
        this.campaignTransactionMapper = campaignTransactionMapper;
    }

    /**
     * Save a campaignTransaction.
     *
     * @param campaignTransactionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CampaignTransactionDTO save(CampaignTransactionDTO campaignTransactionDTO) {
        log.debug("Request to save CampaignTransaction : {}", campaignTransactionDTO);
        CampaignTransaction campaignTransaction = campaignTransactionMapper.toEntity(campaignTransactionDTO);
        campaignTransaction = campaignTransactionRepository.save(campaignTransaction);
        return campaignTransactionMapper.toDto(campaignTransaction);
    }

    /**
     * Get all the campaignTransactions.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CampaignTransactionDTO> findAll() {
        log.debug("Request to get all CampaignTransactions");
        return campaignTransactionRepository.findAll().stream()
            .map(campaignTransactionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one campaignTransaction by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampaignTransactionDTO> findOne(Long id) {
        log.debug("Request to get CampaignTransaction : {}", id);
        return campaignTransactionRepository.findById(id)
            .map(campaignTransactionMapper::toDto);
    }

    /**
     * Delete the campaignTransaction by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampaignTransaction : {}", id);
        campaignTransactionRepository.deleteById(id);
    }
}
