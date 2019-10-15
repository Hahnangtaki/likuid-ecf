package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.CampaignInvestor;
import id.tech.cakra.likuidecf.repository.CampaignInvestorRepository;
import id.tech.cakra.likuidecf.service.CampaignInvestorService;
import id.tech.cakra.likuidecf.service.dto.CampaignInvestorDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignInvestorMapper;
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
 * Integration tests for the {@link CampaignInvestorResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class CampaignInvestorResourceIT {

    private static final Long DEFAULT_QTY = 1L;
    private static final Long UPDATED_QTY = 2L;

    private static final Double DEFAULT_FUND_AMOUNT = 1D;
    private static final Double UPDATED_FUND_AMOUNT = 2D;

    @Autowired
    private CampaignInvestorRepository campaignInvestorRepository;

    @Autowired
    private CampaignInvestorMapper campaignInvestorMapper;

    @Autowired
    private CampaignInvestorService campaignInvestorService;

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

    private MockMvc restCampaignInvestorMockMvc;

    private CampaignInvestor campaignInvestor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignInvestorResource campaignInvestorResource = new CampaignInvestorResource(campaignInvestorService);
        this.restCampaignInvestorMockMvc = MockMvcBuilders.standaloneSetup(campaignInvestorResource)
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
    public static CampaignInvestor createEntity(EntityManager em) {
        CampaignInvestor campaignInvestor = new CampaignInvestor()
            .qty(DEFAULT_QTY)
            .fundAmount(DEFAULT_FUND_AMOUNT);
        return campaignInvestor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignInvestor createUpdatedEntity(EntityManager em) {
        CampaignInvestor campaignInvestor = new CampaignInvestor()
            .qty(UPDATED_QTY)
            .fundAmount(UPDATED_FUND_AMOUNT);
        return campaignInvestor;
    }

    @BeforeEach
    public void initTest() {
        campaignInvestor = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignInvestor() throws Exception {
        int databaseSizeBeforeCreate = campaignInvestorRepository.findAll().size();

        // Create the CampaignInvestor
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(campaignInvestor);
        restCampaignInvestorMockMvc.perform(post("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignInvestor testCampaignInvestor = campaignInvestorList.get(campaignInvestorList.size() - 1);
        assertThat(testCampaignInvestor.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testCampaignInvestor.getFundAmount()).isEqualTo(DEFAULT_FUND_AMOUNT);
    }

    @Test
    @Transactional
    public void createCampaignInvestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignInvestorRepository.findAll().size();

        // Create the CampaignInvestor with an existing ID
        campaignInvestor.setId(1L);
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(campaignInvestor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignInvestorMockMvc.perform(post("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignInvestors() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        // Get all the campaignInvestorList
        restCampaignInvestorMockMvc.perform(get("/api/campaign-investors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignInvestor.getId().intValue())))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY.intValue())))
            .andExpect(jsonPath("$.[*].fundAmount").value(hasItem(DEFAULT_FUND_AMOUNT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getCampaignInvestor() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        // Get the campaignInvestor
        restCampaignInvestorMockMvc.perform(get("/api/campaign-investors/{id}", campaignInvestor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignInvestor.getId().intValue()))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY.intValue()))
            .andExpect(jsonPath("$.fundAmount").value(DEFAULT_FUND_AMOUNT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignInvestor() throws Exception {
        // Get the campaignInvestor
        restCampaignInvestorMockMvc.perform(get("/api/campaign-investors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignInvestor() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        int databaseSizeBeforeUpdate = campaignInvestorRepository.findAll().size();

        // Update the campaignInvestor
        CampaignInvestor updatedCampaignInvestor = campaignInvestorRepository.findById(campaignInvestor.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignInvestor are not directly saved in db
        em.detach(updatedCampaignInvestor);
        updatedCampaignInvestor
            .qty(UPDATED_QTY)
            .fundAmount(UPDATED_FUND_AMOUNT);
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(updatedCampaignInvestor);

        restCampaignInvestorMockMvc.perform(put("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeUpdate);
        CampaignInvestor testCampaignInvestor = campaignInvestorList.get(campaignInvestorList.size() - 1);
        assertThat(testCampaignInvestor.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testCampaignInvestor.getFundAmount()).isEqualTo(UPDATED_FUND_AMOUNT);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignInvestor() throws Exception {
        int databaseSizeBeforeUpdate = campaignInvestorRepository.findAll().size();

        // Create the CampaignInvestor
        CampaignInvestorDTO campaignInvestorDTO = campaignInvestorMapper.toDto(campaignInvestor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignInvestorMockMvc.perform(put("/api/campaign-investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignInvestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignInvestor in the database
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignInvestor() throws Exception {
        // Initialize the database
        campaignInvestorRepository.saveAndFlush(campaignInvestor);

        int databaseSizeBeforeDelete = campaignInvestorRepository.findAll().size();

        // Delete the campaignInvestor
        restCampaignInvestorMockMvc.perform(delete("/api/campaign-investors/{id}", campaignInvestor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignInvestor> campaignInvestorList = campaignInvestorRepository.findAll();
        assertThat(campaignInvestorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignInvestor.class);
        CampaignInvestor campaignInvestor1 = new CampaignInvestor();
        campaignInvestor1.setId(1L);
        CampaignInvestor campaignInvestor2 = new CampaignInvestor();
        campaignInvestor2.setId(campaignInvestor1.getId());
        assertThat(campaignInvestor1).isEqualTo(campaignInvestor2);
        campaignInvestor2.setId(2L);
        assertThat(campaignInvestor1).isNotEqualTo(campaignInvestor2);
        campaignInvestor1.setId(null);
        assertThat(campaignInvestor1).isNotEqualTo(campaignInvestor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignInvestorDTO.class);
        CampaignInvestorDTO campaignInvestorDTO1 = new CampaignInvestorDTO();
        campaignInvestorDTO1.setId(1L);
        CampaignInvestorDTO campaignInvestorDTO2 = new CampaignInvestorDTO();
        assertThat(campaignInvestorDTO1).isNotEqualTo(campaignInvestorDTO2);
        campaignInvestorDTO2.setId(campaignInvestorDTO1.getId());
        assertThat(campaignInvestorDTO1).isEqualTo(campaignInvestorDTO2);
        campaignInvestorDTO2.setId(2L);
        assertThat(campaignInvestorDTO1).isNotEqualTo(campaignInvestorDTO2);
        campaignInvestorDTO1.setId(null);
        assertThat(campaignInvestorDTO1).isNotEqualTo(campaignInvestorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campaignInvestorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campaignInvestorMapper.fromId(null)).isNull();
    }
}
