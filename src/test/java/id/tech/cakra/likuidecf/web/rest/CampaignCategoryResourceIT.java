package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.CampaignCategory;
import id.tech.cakra.likuidecf.repository.CampaignCategoryRepository;
import id.tech.cakra.likuidecf.service.CampaignCategoryService;
import id.tech.cakra.likuidecf.service.dto.CampaignCategoryDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignCategoryMapper;
import id.tech.cakra.likuidecf.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static id.tech.cakra.likuidecf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CampaignCategoryResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class CampaignCategoryResourceIT {

    @Autowired
    private CampaignCategoryRepository campaignCategoryRepository;

    @Autowired
    private CampaignCategoryMapper campaignCategoryMapper;

    @Autowired
    private CampaignCategoryService campaignCategoryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restCampaignCategoryMockMvc;

    private CampaignCategory campaignCategory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignCategoryResource campaignCategoryResource = new CampaignCategoryResource(campaignCategoryService);
        this.restCampaignCategoryMockMvc = MockMvcBuilders.standaloneSetup(campaignCategoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignCategory createEntity(EntityManager em) {
        CampaignCategory campaignCategory = new CampaignCategory();
        return campaignCategory;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignCategory createUpdatedEntity(EntityManager em) {
        CampaignCategory campaignCategory = new CampaignCategory();
        return campaignCategory;
    }

    @BeforeEach
    public void initTest() {
        campaignCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignCategory() throws Exception {
        int databaseSizeBeforeCreate = campaignCategoryRepository.findAll().size();

        // Create the CampaignCategory
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(campaignCategory);
        restCampaignCategoryMockMvc.perform(post("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignCategory testCampaignCategory = campaignCategoryList.get(campaignCategoryList.size() - 1);
    }

    @Test
    @Transactional
    public void createCampaignCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignCategoryRepository.findAll().size();

        // Create the CampaignCategory with an existing ID
        campaignCategory.setId(1L);
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(campaignCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignCategoryMockMvc.perform(post("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignCategories() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        // Get all the campaignCategoryList
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignCategory.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        // Get the campaignCategory
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories/{id}", campaignCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignCategory.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignCategory() throws Exception {
        // Get the campaignCategory
        restCampaignCategoryMockMvc.perform(get("/api/campaign-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        int databaseSizeBeforeUpdate = campaignCategoryRepository.findAll().size();

        // Update the campaignCategory
        CampaignCategory updatedCampaignCategory = campaignCategoryRepository.findById(campaignCategory.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignCategory are not directly saved in db
        em.detach(updatedCampaignCategory);
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(updatedCampaignCategory);

        restCampaignCategoryMockMvc.perform(put("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeUpdate);
        CampaignCategory testCampaignCategory = campaignCategoryList.get(campaignCategoryList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignCategory() throws Exception {
        int databaseSizeBeforeUpdate = campaignCategoryRepository.findAll().size();

        // Create the CampaignCategory
        CampaignCategoryDTO campaignCategoryDTO = campaignCategoryMapper.toDto(campaignCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignCategoryMockMvc.perform(put("/api/campaign-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignCategory in the database
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignCategory() throws Exception {
        // Initialize the database
        campaignCategoryRepository.saveAndFlush(campaignCategory);

        int databaseSizeBeforeDelete = campaignCategoryRepository.findAll().size();

        // Delete the campaignCategory
        restCampaignCategoryMockMvc.perform(delete("/api/campaign-categories/{id}", campaignCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignCategory> campaignCategoryList = campaignCategoryRepository.findAll();
        assertThat(campaignCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignCategory.class);
        CampaignCategory campaignCategory1 = new CampaignCategory();
        campaignCategory1.setId(1L);
        CampaignCategory campaignCategory2 = new CampaignCategory();
        campaignCategory2.setId(campaignCategory1.getId());
        assertThat(campaignCategory1).isEqualTo(campaignCategory2);
        campaignCategory2.setId(2L);
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
        campaignCategory1.setId(null);
        assertThat(campaignCategory1).isNotEqualTo(campaignCategory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignCategoryDTO.class);
        CampaignCategoryDTO campaignCategoryDTO1 = new CampaignCategoryDTO();
        campaignCategoryDTO1.setId(1L);
        CampaignCategoryDTO campaignCategoryDTO2 = new CampaignCategoryDTO();
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO2.setId(campaignCategoryDTO1.getId());
        assertThat(campaignCategoryDTO1).isEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO2.setId(2L);
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO1.setId(null);
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campaignCategoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campaignCategoryMapper.fromId(null)).isNull();
    }
}
