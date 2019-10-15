package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.FundRaiser;
import id.tech.cakra.likuidecf.repository.FundRaiserRepository;
import id.tech.cakra.likuidecf.service.FundRaiserService;
import id.tech.cakra.likuidecf.service.dto.FundRaiserDTO;
import id.tech.cakra.likuidecf.service.mapper.FundRaiserMapper;
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
 * Integration tests for the {@link FundRaiserResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class FundRaiserResourceIT {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR_FOUNDED = 1;
    private static final Integer UPDATED_YEAR_FOUNDED = 2;

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    @Autowired
    private FundRaiserRepository fundRaiserRepository;

    @Autowired
    private FundRaiserMapper fundRaiserMapper;

    @Autowired
    private FundRaiserService fundRaiserService;

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

    private MockMvc restFundRaiserMockMvc;

    private FundRaiser fundRaiser;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FundRaiserResource fundRaiserResource = new FundRaiserResource(fundRaiserService);
        this.restFundRaiserMockMvc = MockMvcBuilders.standaloneSetup(fundRaiserResource)
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
    public static FundRaiser createEntity(EntityManager em) {
        FundRaiser fundRaiser = new FundRaiser()
            .companyName(DEFAULT_COMPANY_NAME)
            .yearFounded(DEFAULT_YEAR_FOUNDED)
            .website(DEFAULT_WEBSITE);
        return fundRaiser;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundRaiser createUpdatedEntity(EntityManager em) {
        FundRaiser fundRaiser = new FundRaiser()
            .companyName(UPDATED_COMPANY_NAME)
            .yearFounded(UPDATED_YEAR_FOUNDED)
            .website(UPDATED_WEBSITE);
        return fundRaiser;
    }

    @BeforeEach
    public void initTest() {
        fundRaiser = createEntity(em);
    }

    @Test
    @Transactional
    public void createFundRaiser() throws Exception {
        int databaseSizeBeforeCreate = fundRaiserRepository.findAll().size();

        // Create the FundRaiser
        FundRaiserDTO fundRaiserDTO = fundRaiserMapper.toDto(fundRaiser);
        restFundRaiserMockMvc.perform(post("/api/fund-raisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserDTO)))
            .andExpect(status().isCreated());

        // Validate the FundRaiser in the database
        List<FundRaiser> fundRaiserList = fundRaiserRepository.findAll();
        assertThat(fundRaiserList).hasSize(databaseSizeBeforeCreate + 1);
        FundRaiser testFundRaiser = fundRaiserList.get(fundRaiserList.size() - 1);
        assertThat(testFundRaiser.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testFundRaiser.getYearFounded()).isEqualTo(DEFAULT_YEAR_FOUNDED);
        assertThat(testFundRaiser.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
    }

    @Test
    @Transactional
    public void createFundRaiserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fundRaiserRepository.findAll().size();

        // Create the FundRaiser with an existing ID
        fundRaiser.setId(1L);
        FundRaiserDTO fundRaiserDTO = fundRaiserMapper.toDto(fundRaiser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundRaiserMockMvc.perform(post("/api/fund-raisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundRaiser in the database
        List<FundRaiser> fundRaiserList = fundRaiserRepository.findAll();
        assertThat(fundRaiserList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFundRaisers() throws Exception {
        // Initialize the database
        fundRaiserRepository.saveAndFlush(fundRaiser);

        // Get all the fundRaiserList
        restFundRaiserMockMvc.perform(get("/api/fund-raisers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundRaiser.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].yearFounded").value(hasItem(DEFAULT_YEAR_FOUNDED)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)));
    }
    
    @Test
    @Transactional
    public void getFundRaiser() throws Exception {
        // Initialize the database
        fundRaiserRepository.saveAndFlush(fundRaiser);

        // Get the fundRaiser
        restFundRaiserMockMvc.perform(get("/api/fund-raisers/{id}", fundRaiser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fundRaiser.getId().intValue()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.yearFounded").value(DEFAULT_YEAR_FOUNDED))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE));
    }

    @Test
    @Transactional
    public void getNonExistingFundRaiser() throws Exception {
        // Get the fundRaiser
        restFundRaiserMockMvc.perform(get("/api/fund-raisers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFundRaiser() throws Exception {
        // Initialize the database
        fundRaiserRepository.saveAndFlush(fundRaiser);

        int databaseSizeBeforeUpdate = fundRaiserRepository.findAll().size();

        // Update the fundRaiser
        FundRaiser updatedFundRaiser = fundRaiserRepository.findById(fundRaiser.getId()).get();
        // Disconnect from session so that the updates on updatedFundRaiser are not directly saved in db
        em.detach(updatedFundRaiser);
        updatedFundRaiser
            .companyName(UPDATED_COMPANY_NAME)
            .yearFounded(UPDATED_YEAR_FOUNDED)
            .website(UPDATED_WEBSITE);
        FundRaiserDTO fundRaiserDTO = fundRaiserMapper.toDto(updatedFundRaiser);

        restFundRaiserMockMvc.perform(put("/api/fund-raisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserDTO)))
            .andExpect(status().isOk());

        // Validate the FundRaiser in the database
        List<FundRaiser> fundRaiserList = fundRaiserRepository.findAll();
        assertThat(fundRaiserList).hasSize(databaseSizeBeforeUpdate);
        FundRaiser testFundRaiser = fundRaiserList.get(fundRaiserList.size() - 1);
        assertThat(testFundRaiser.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testFundRaiser.getYearFounded()).isEqualTo(UPDATED_YEAR_FOUNDED);
        assertThat(testFundRaiser.getWebsite()).isEqualTo(UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    public void updateNonExistingFundRaiser() throws Exception {
        int databaseSizeBeforeUpdate = fundRaiserRepository.findAll().size();

        // Create the FundRaiser
        FundRaiserDTO fundRaiserDTO = fundRaiserMapper.toDto(fundRaiser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundRaiserMockMvc.perform(put("/api/fund-raisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundRaiser in the database
        List<FundRaiser> fundRaiserList = fundRaiserRepository.findAll();
        assertThat(fundRaiserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFundRaiser() throws Exception {
        // Initialize the database
        fundRaiserRepository.saveAndFlush(fundRaiser);

        int databaseSizeBeforeDelete = fundRaiserRepository.findAll().size();

        // Delete the fundRaiser
        restFundRaiserMockMvc.perform(delete("/api/fund-raisers/{id}", fundRaiser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FundRaiser> fundRaiserList = fundRaiserRepository.findAll();
        assertThat(fundRaiserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundRaiser.class);
        FundRaiser fundRaiser1 = new FundRaiser();
        fundRaiser1.setId(1L);
        FundRaiser fundRaiser2 = new FundRaiser();
        fundRaiser2.setId(fundRaiser1.getId());
        assertThat(fundRaiser1).isEqualTo(fundRaiser2);
        fundRaiser2.setId(2L);
        assertThat(fundRaiser1).isNotEqualTo(fundRaiser2);
        fundRaiser1.setId(null);
        assertThat(fundRaiser1).isNotEqualTo(fundRaiser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundRaiserDTO.class);
        FundRaiserDTO fundRaiserDTO1 = new FundRaiserDTO();
        fundRaiserDTO1.setId(1L);
        FundRaiserDTO fundRaiserDTO2 = new FundRaiserDTO();
        assertThat(fundRaiserDTO1).isNotEqualTo(fundRaiserDTO2);
        fundRaiserDTO2.setId(fundRaiserDTO1.getId());
        assertThat(fundRaiserDTO1).isEqualTo(fundRaiserDTO2);
        fundRaiserDTO2.setId(2L);
        assertThat(fundRaiserDTO1).isNotEqualTo(fundRaiserDTO2);
        fundRaiserDTO1.setId(null);
        assertThat(fundRaiserDTO1).isNotEqualTo(fundRaiserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fundRaiserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fundRaiserMapper.fromId(null)).isNull();
    }
}
