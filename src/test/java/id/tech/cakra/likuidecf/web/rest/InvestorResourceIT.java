package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.Investor;
import id.tech.cakra.likuidecf.repository.InvestorRepository;
import id.tech.cakra.likuidecf.service.InvestorService;
import id.tech.cakra.likuidecf.service.dto.InvestorDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorMapper;
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
 * Integration tests for the {@link InvestorResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class InvestorResourceIT {

    private static final String DEFAULT_INVESTOR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INVESTOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTOR_TYPE = "A";
    private static final String UPDATED_INVESTOR_TYPE = "B";

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private InvestorMapper investorMapper;

    @Autowired
    private InvestorService investorService;

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

    private MockMvc restInvestorMockMvc;

    private Investor investor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorResource investorResource = new InvestorResource(investorService);
        this.restInvestorMockMvc = MockMvcBuilders.standaloneSetup(investorResource)
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
    public static Investor createEntity(EntityManager em) {
        Investor investor = new Investor()
            .investorCode(DEFAULT_INVESTOR_CODE)
            .investorName(DEFAULT_INVESTOR_NAME)
            .investorType(DEFAULT_INVESTOR_TYPE);
        return investor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Investor createUpdatedEntity(EntityManager em) {
        Investor investor = new Investor()
            .investorCode(UPDATED_INVESTOR_CODE)
            .investorName(UPDATED_INVESTOR_NAME)
            .investorType(UPDATED_INVESTOR_TYPE);
        return investor;
    }

    @BeforeEach
    public void initTest() {
        investor = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestor() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.toDto(investor);
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isCreated());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate + 1);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.getInvestorCode()).isEqualTo(DEFAULT_INVESTOR_CODE);
        assertThat(testInvestor.getInvestorName()).isEqualTo(DEFAULT_INVESTOR_NAME);
        assertThat(testInvestor.getInvestorType()).isEqualTo(DEFAULT_INVESTOR_TYPE);
    }

    @Test
    @Transactional
    public void createInvestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorRepository.findAll().size();

        // Create the Investor with an existing ID
        investor.setId(1L);
        InvestorDTO investorDTO = investorMapper.toDto(investor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorMockMvc.perform(post("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvestors() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        // Get all the investorList
        restInvestorMockMvc.perform(get("/api/investors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investor.getId().intValue())))
            .andExpect(jsonPath("$.[*].investorCode").value(hasItem(DEFAULT_INVESTOR_CODE)))
            .andExpect(jsonPath("$.[*].investorName").value(hasItem(DEFAULT_INVESTOR_NAME)))
            .andExpect(jsonPath("$.[*].investorType").value(hasItem(DEFAULT_INVESTOR_TYPE)));
    }
    
    @Test
    @Transactional
    public void getInvestor() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", investor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investor.getId().intValue()))
            .andExpect(jsonPath("$.investorCode").value(DEFAULT_INVESTOR_CODE))
            .andExpect(jsonPath("$.investorName").value(DEFAULT_INVESTOR_NAME))
            .andExpect(jsonPath("$.investorType").value(DEFAULT_INVESTOR_TYPE));
    }

    @Test
    @Transactional
    public void getNonExistingInvestor() throws Exception {
        // Get the investor
        restInvestorMockMvc.perform(get("/api/investors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestor() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Update the investor
        Investor updatedInvestor = investorRepository.findById(investor.getId()).get();
        // Disconnect from session so that the updates on updatedInvestor are not directly saved in db
        em.detach(updatedInvestor);
        updatedInvestor
            .investorCode(UPDATED_INVESTOR_CODE)
            .investorName(UPDATED_INVESTOR_NAME)
            .investorType(UPDATED_INVESTOR_TYPE);
        InvestorDTO investorDTO = investorMapper.toDto(updatedInvestor);

        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isOk());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate);
        Investor testInvestor = investorList.get(investorList.size() - 1);
        assertThat(testInvestor.getInvestorCode()).isEqualTo(UPDATED_INVESTOR_CODE);
        assertThat(testInvestor.getInvestorName()).isEqualTo(UPDATED_INVESTOR_NAME);
        assertThat(testInvestor.getInvestorType()).isEqualTo(UPDATED_INVESTOR_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestor() throws Exception {
        int databaseSizeBeforeUpdate = investorRepository.findAll().size();

        // Create the Investor
        InvestorDTO investorDTO = investorMapper.toDto(investor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvestorMockMvc.perform(put("/api/investors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Investor in the database
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestor() throws Exception {
        // Initialize the database
        investorRepository.saveAndFlush(investor);

        int databaseSizeBeforeDelete = investorRepository.findAll().size();

        // Delete the investor
        restInvestorMockMvc.perform(delete("/api/investors/{id}", investor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Investor> investorList = investorRepository.findAll();
        assertThat(investorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Investor.class);
        Investor investor1 = new Investor();
        investor1.setId(1L);
        Investor investor2 = new Investor();
        investor2.setId(investor1.getId());
        assertThat(investor1).isEqualTo(investor2);
        investor2.setId(2L);
        assertThat(investor1).isNotEqualTo(investor2);
        investor1.setId(null);
        assertThat(investor1).isNotEqualTo(investor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorDTO.class);
        InvestorDTO investorDTO1 = new InvestorDTO();
        investorDTO1.setId(1L);
        InvestorDTO investorDTO2 = new InvestorDTO();
        assertThat(investorDTO1).isNotEqualTo(investorDTO2);
        investorDTO2.setId(investorDTO1.getId());
        assertThat(investorDTO1).isEqualTo(investorDTO2);
        investorDTO2.setId(2L);
        assertThat(investorDTO1).isNotEqualTo(investorDTO2);
        investorDTO1.setId(null);
        assertThat(investorDTO1).isNotEqualTo(investorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorMapper.fromId(null)).isNull();
    }
}
