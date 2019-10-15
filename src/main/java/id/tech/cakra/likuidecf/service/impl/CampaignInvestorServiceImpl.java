package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.CampaignInvestorService;
import id.tech.cakra.likuidecf.domain.CampaignInvestor;
import id.tech.cakra.likuidecf.repository.CampaignInvestorRepository;
import id.tech.cakra.likuidecf.service.dto.CampaignInvestorDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignInvestorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CampaignInvestor}.
 */
@Service
@Transactional
public class CampaignInvestorServiceImpl implements CampaignInvestorService {

    private final Logger log = LoggerFactory.getLogger(CampaignInvestorServiceImpl.class);

    private final CampaignInvestorRepository campaignInvestorRepository;

    private final CampaignInvestorMapper campaignInvestorMapper;

    public CampaignInvestorServiceImpl(CampaignInvestorRepository campaignInvestorRepository, CampaignInvestorMapper campaignInvestorMapper) {
        this.campaignInvestorRepository = campaignInvestorRepository;
        this.campaignInvestorMapper = campaignInvestorMapper;
    }

    /**
     * Save a campaignInvestor.
     *
     * @param campaignInvestorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CampaignInvestorDTO save(CampaignInvestorDTO campaignInvestorDTO) {
        log.debug("Request to save CampaignInvestor : {}", campaignInvestorDTO);
        CampaignInvestor campaignInvestor = campaignInvestorMapper.toEntity(campaignInvestorDTO);
        campaignInvestor = campaignInvestorRepository.save(campaignInvestor);
        return campaignInvestorMapper.toDto(campaignInvestor);
    }

    /**
     * Get all the campaignInvestors.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CampaignInvestorDTO> findAll() {
        log.debug("Request to get all CampaignInvestors");
        return campaignInvestorRepository.findAll().stream()
            .map(campaignInvestorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one campaignInvestor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampaignInvestorDTO> findOne(Long id) {
        log.debug("Request to get CampaignInvestor : {}", id);
        return campaignInvestorRepository.findById(id)
            .map(campaignInvestorMapper::toDto);
    }

    /**
     * Delete the campaignInvestor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampaignInvestor : {}", id);
        campaignInvestorRepository.deleteById(id);
    }
}
