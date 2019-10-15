package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.CampaignPaymentService;
import id.tech.cakra.likuidecf.domain.CampaignPayment;
import id.tech.cakra.likuidecf.repository.CampaignPaymentRepository;
import id.tech.cakra.likuidecf.service.dto.CampaignPaymentDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignPaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CampaignPayment}.
 */
@Service
@Transactional
public class CampaignPaymentServiceImpl implements CampaignPaymentService {

    private final Logger log = LoggerFactory.getLogger(CampaignPaymentServiceImpl.class);

    private final CampaignPaymentRepository campaignPaymentRepository;

    private final CampaignPaymentMapper campaignPaymentMapper;

    public CampaignPaymentServiceImpl(CampaignPaymentRepository campaignPaymentRepository, CampaignPaymentMapper campaignPaymentMapper) {
        this.campaignPaymentRepository = campaignPaymentRepository;
        this.campaignPaymentMapper = campaignPaymentMapper;
    }

    /**
     * Save a campaignPayment.
     *
     * @param campaignPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CampaignPaymentDTO save(CampaignPaymentDTO campaignPaymentDTO) {
        log.debug("Request to save CampaignPayment : {}", campaignPaymentDTO);
        CampaignPayment campaignPayment = campaignPaymentMapper.toEntity(campaignPaymentDTO);
        campaignPayment = campaignPaymentRepository.save(campaignPayment);
        return campaignPaymentMapper.toDto(campaignPayment);
    }

    /**
     * Get all the campaignPayments.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CampaignPaymentDTO> findAll() {
        log.debug("Request to get all CampaignPayments");
        return campaignPaymentRepository.findAll().stream()
            .map(campaignPaymentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one campaignPayment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampaignPaymentDTO> findOne(Long id) {
        log.debug("Request to get CampaignPayment : {}", id);
        return campaignPaymentRepository.findById(id)
            .map(campaignPaymentMapper::toDto);
    }

    /**
     * Delete the campaignPayment by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampaignPayment : {}", id);
        campaignPaymentRepository.deleteById(id);
    }
}
