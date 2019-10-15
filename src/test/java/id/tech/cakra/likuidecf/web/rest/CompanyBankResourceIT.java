package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.CompanyBank;
import id.tech.cakra.likuidecf.repository.CompanyBankRepository;
import id.tech.cakra.likuidecf.service.CompanyBankService;
import id.tech.cakra.likuidecf.service.dto.CompanyBankDTO;
import id.tech.cakra.likuidecf.service.mapper.CompanyBankMapper;
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
 * Integration tests for the {@link CompanyBankResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class CompanyBankResourceIT {

    private static final String DEFAULT_BANK_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BANK_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    @Autowired
    private CompanyBankRepository companyBankRepository;

    @Autowired
    private CompanyBankMapper companyBankMapper;

    @Autowired
    private CompanyBankService companyBankService;

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

    private MockMvc restCompanyBankMockMvc;

    private CompanyBank companyBank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompanyBankResource companyBankResource = new CompanyBankResource(companyBankService);
        this.restCompanyBankMockMvc = MockMvcBuilders.standaloneSetup(companyBankResource)
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
    public static CompanyBank createEntity(EntityManager em) {
        CompanyBank companyBank = new CompanyBank()
            .bankAccountNo(DEFAULT_BANK_ACCOUNT_NO)
            .bankAccountName(DEFAULT_BANK_ACCOUNT_NAME)
            .bankBranch(DEFAULT_BANK_BRANCH)
            .status(DEFAULT_STATUS);
        return companyBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CompanyBank createUpdatedEntity(EntityManager em) {
        CompanyBank companyBank = new CompanyBank()
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS);
        return companyBank;
    }

    @BeforeEach
    public void initTest() {
        companyBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompanyBank() throws Exception {
        int databaseSizeBeforeCreate = companyBankRepository.findAll().size();

        // Create the CompanyBank
        CompanyBankDTO companyBankDTO = companyBankMapper.toDto(companyBank);
        restCompanyBankMockMvc.perform(post("/api/company-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyBankDTO)))
            .andExpect(status().isCreated());

        // Validate the CompanyBank in the database
        List<CompanyBank> companyBankList = companyBankRepository.findAll();
        assertThat(companyBankList).hasSize(databaseSizeBeforeCreate + 1);
        CompanyBank testCompanyBank = companyBankList.get(companyBankList.size() - 1);
        assertThat(testCompanyBank.getBankAccountNo()).isEqualTo(DEFAULT_BANK_ACCOUNT_NO);
        assertThat(testCompanyBank.getBankAccountName()).isEqualTo(DEFAULT_BANK_ACCOUNT_NAME);
        assertThat(testCompanyBank.getBankBranch()).isEqualTo(DEFAULT_BANK_BRANCH);
        assertThat(testCompanyBank.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCompanyBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = companyBankRepository.findAll().size();

        // Create the CompanyBank with an existing ID
        companyBank.setId(1L);
        CompanyBankDTO companyBankDTO = companyBankMapper.toDto(companyBank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanyBankMockMvc.perform(post("/api/company-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyBank in the database
        List<CompanyBank> companyBankList = companyBankRepository.findAll();
        assertThat(companyBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCompanyBanks() throws Exception {
        // Initialize the database
        companyBankRepository.saveAndFlush(companyBank);

        // Get all the companyBankList
        restCompanyBankMockMvc.perform(get("/api/company-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companyBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankAccountNo").value(hasItem(DEFAULT_BANK_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].bankAccountName").value(hasItem(DEFAULT_BANK_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].bankBranch").value(hasItem(DEFAULT_BANK_BRANCH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getCompanyBank() throws Exception {
        // Initialize the database
        companyBankRepository.saveAndFlush(companyBank);

        // Get the companyBank
        restCompanyBankMockMvc.perform(get("/api/company-banks/{id}", companyBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(companyBank.getId().intValue()))
            .andExpect(jsonPath("$.bankAccountNo").value(DEFAULT_BANK_ACCOUNT_NO))
            .andExpect(jsonPath("$.bankAccountName").value(DEFAULT_BANK_ACCOUNT_NAME))
            .andExpect(jsonPath("$.bankBranch").value(DEFAULT_BANK_BRANCH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingCompanyBank() throws Exception {
        // Get the companyBank
        restCompanyBankMockMvc.perform(get("/api/company-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompanyBank() throws Exception {
        // Initialize the database
        companyBankRepository.saveAndFlush(companyBank);

        int databaseSizeBeforeUpdate = companyBankRepository.findAll().size();

        // Update the companyBank
        CompanyBank updatedCompanyBank = companyBankRepository.findById(companyBank.getId()).get();
        // Disconnect from session so that the updates on updatedCompanyBank are not directly saved in db
        em.detach(updatedCompanyBank);
        updatedCompanyBank
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS);
        CompanyBankDTO companyBankDTO = companyBankMapper.toDto(updatedCompanyBank);

        restCompanyBankMockMvc.perform(put("/api/company-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyBankDTO)))
            .andExpect(status().isOk());

        // Validate the CompanyBank in the database
        List<CompanyBank> companyBankList = companyBankRepository.findAll();
        assertThat(companyBankList).hasSize(databaseSizeBeforeUpdate);
        CompanyBank testCompanyBank = companyBankList.get(companyBankList.size() - 1);
        assertThat(testCompanyBank.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testCompanyBank.getBankAccountName()).isEqualTo(UPDATED_BANK_ACCOUNT_NAME);
        assertThat(testCompanyBank.getBankBranch()).isEqualTo(UPDATED_BANK_BRANCH);
        assertThat(testCompanyBank.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCompanyBank() throws Exception {
        int databaseSizeBeforeUpdate = companyBankRepository.findAll().size();

        // Create the CompanyBank
        CompanyBankDTO companyBankDTO = companyBankMapper.toDto(companyBank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanyBankMockMvc.perform(put("/api/company-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(companyBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CompanyBank in the database
        List<CompanyBank> companyBankList = companyBankRepository.findAll();
        assertThat(companyBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompanyBank() throws Exception {
        // Initialize the database
        companyBankRepository.saveAndFlush(companyBank);

        int databaseSizeBeforeDelete = companyBankRepository.findAll().size();

        // Delete the companyBank
        restCompanyBankMockMvc.perform(delete("/api/company-banks/{id}", companyBank.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CompanyBank> companyBankList = companyBankRepository.findAll();
        assertThat(companyBankList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyBank.class);
        CompanyBank companyBank1 = new CompanyBank();
        companyBank1.setId(1L);
        CompanyBank companyBank2 = new CompanyBank();
        companyBank2.setId(companyBank1.getId());
        assertThat(companyBank1).isEqualTo(companyBank2);
        companyBank2.setId(2L);
        assertThat(companyBank1).isNotEqualTo(companyBank2);
        companyBank1.setId(null);
        assertThat(companyBank1).isNotEqualTo(companyBank2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyBankDTO.class);
        CompanyBankDTO companyBankDTO1 = new CompanyBankDTO();
        companyBankDTO1.setId(1L);
        CompanyBankDTO companyBankDTO2 = new CompanyBankDTO();
        assertThat(companyBankDTO1).isNotEqualTo(companyBankDTO2);
        companyBankDTO2.setId(companyBankDTO1.getId());
        assertThat(companyBankDTO1).isEqualTo(companyBankDTO2);
        companyBankDTO2.setId(2L);
        assertThat(companyBankDTO1).isNotEqualTo(companyBankDTO2);
        companyBankDTO1.setId(null);
        assertThat(companyBankDTO1).isNotEqualTo(companyBankDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(companyBankMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(companyBankMapper.fromId(null)).isNull();
    }
}
