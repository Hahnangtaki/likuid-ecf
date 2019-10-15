package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.CampaignCategoryService;
import id.tech.cakra.likuidecf.domain.CampaignCategory;
import id.tech.cakra.likuidecf.repository.CampaignCategoryRepository;
import id.tech.cakra.likuidecf.service.dto.CampaignCategoryDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CampaignCategory}.
 */
@Service
@Transactional
public class CampaignCategoryServiceImpl implements CampaignCategoryService {

    private final Logger log = LoggerFactory.getLogger(CampaignCategoryServiceImpl.class);

    private final CampaignCategoryRepository campaignCategoryRepository;

    private final CampaignCategoryMapper campaignCategoryMapper;

    public CampaignCategoryServiceImpl(CampaignCategoryRepository campaignCategoryRepository, CampaignCategoryMapper campaignCategoryMapper) {
        this.campaignCategoryRepository = campaignCategoryRepository;
        this.campaignCategoryMapper = campaignCategoryMapper;
    }

    /**
     * Save a campaignCategory.
     *
     * @param campaignCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CampaignCategoryDTO save(CampaignCategoryDTO campaignCategoryDTO) {
        log.debug("Request to save CampaignCategory : {}", campaignCategoryDTO);
        CampaignCategory campaignCategory = campaignCategoryMapper.toEntity(campaignCategoryDTO);
        campaignCategory = campaignCategoryRepository.save(campaignCategory);
        return campaignCategoryMapper.toDto(campaignCategory);
    }

    /**
     * Get all the campaignCategories.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CampaignCategoryDTO> findAll() {
        log.debug("Request to get all CampaignCategories");
        return campaignCategoryRepository.findAll().stream()
            .map(campaignCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one campaignCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampaignCategoryDTO> findOne(Long id) {
        log.debug("Request to get CampaignCategory : {}", id);
        return campaignCategoryRepository.findById(id)
            .map(campaignCategoryMapper::toDto);
    }

    /**
     * Delete the campaignCategory by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampaignCategory : {}", id);
        campaignCategoryRepository.deleteById(id);
    }
}
