package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.FundRaiserBank;
import id.tech.cakra.likuidecf.repository.FundRaiserBankRepository;
import id.tech.cakra.likuidecf.service.FundRaiserBankService;
import id.tech.cakra.likuidecf.service.dto.FundRaiserBankDTO;
import id.tech.cakra.likuidecf.service.mapper.FundRaiserBankMapper;
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
 * Integration tests for the {@link FundRaiserBankResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class FundRaiserBankResourceIT {

    private static final String DEFAULT_BANK_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BANK_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    @Autowired
    private FundRaiserBankRepository fundRaiserBankRepository;

    @Autowired
    private FundRaiserBankMapper fundRaiserBankMapper;

    @Autowired
    private FundRaiserBankService fundRaiserBankService;

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

    private MockMvc restFundRaiserBankMockMvc;

    private FundRaiserBank fundRaiserBank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FundRaiserBankResource fundRaiserBankResource = new FundRaiserBankResource(fundRaiserBankService);
        this.restFundRaiserBankMockMvc = MockMvcBuilders.standaloneSetup(fundRaiserBankResource)
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
    public static FundRaiserBank createEntity(EntityManager em) {
        FundRaiserBank fundRaiserBank = new FundRaiserBank()
            .bankAccountNo(DEFAULT_BANK_ACCOUNT_NO)
            .bankAccountName(DEFAULT_BANK_ACCOUNT_NAME)
            .bankBranch(DEFAULT_BANK_BRANCH)
            .status(DEFAULT_STATUS);
        return fundRaiserBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundRaiserBank createUpdatedEntity(EntityManager em) {
        FundRaiserBank fundRaiserBank = new FundRaiserBank()
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS);
        return fundRaiserBank;
    }

    @BeforeEach
    public void initTest() {
        fundRaiserBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createFundRaiserBank() throws Exception {
        int databaseSizeBeforeCreate = fundRaiserBankRepository.findAll().size();

        // Create the FundRaiserBank
        FundRaiserBankDTO fundRaiserBankDTO = fundRaiserBankMapper.toDto(fundRaiserBank);
        restFundRaiserBankMockMvc.perform(post("/api/fund-raiser-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserBankDTO)))
            .andExpect(status().isCreated());

        // Validate the FundRaiserBank in the database
        List<FundRaiserBank> fundRaiserBankList = fundRaiserBankRepository.findAll();
        assertThat(fundRaiserBankList).hasSize(databaseSizeBeforeCreate + 1);
        FundRaiserBank testFundRaiserBank = fundRaiserBankList.get(fundRaiserBankList.size() - 1);
        assertThat(testFundRaiserBank.getBankAccountNo()).isEqualTo(DEFAULT_BANK_ACCOUNT_NO);
        assertThat(testFundRaiserBank.getBankAccountName()).isEqualTo(DEFAULT_BANK_ACCOUNT_NAME);
        assertThat(testFundRaiserBank.getBankBranch()).isEqualTo(DEFAULT_BANK_BRANCH);
        assertThat(testFundRaiserBank.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createFundRaiserBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fundRaiserBankRepository.findAll().size();

        // Create the FundRaiserBank with an existing ID
        fundRaiserBank.setId(1L);
        FundRaiserBankDTO fundRaiserBankDTO = fundRaiserBankMapper.toDto(fundRaiserBank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundRaiserBankMockMvc.perform(post("/api/fund-raiser-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundRaiserBank in the database
        List<FundRaiserBank> fundRaiserBankList = fundRaiserBankRepository.findAll();
        assertThat(fundRaiserBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFundRaiserBanks() throws Exception {
        // Initialize the database
        fundRaiserBankRepository.saveAndFlush(fundRaiserBank);

        // Get all the fundRaiserBankList
        restFundRaiserBankMockMvc.perform(get("/api/fund-raiser-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundRaiserBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankAccountNo").value(hasItem(DEFAULT_BANK_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].bankAccountName").value(hasItem(DEFAULT_BANK_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].bankBranch").value(hasItem(DEFAULT_BANK_BRANCH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getFundRaiserBank() throws Exception {
        // Initialize the database
        fundRaiserBankRepository.saveAndFlush(fundRaiserBank);

        // Get the fundRaiserBank
        restFundRaiserBankMockMvc.perform(get("/api/fund-raiser-banks/{id}", fundRaiserBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fundRaiserBank.getId().intValue()))
            .andExpect(jsonPath("$.bankAccountNo").value(DEFAULT_BANK_ACCOUNT_NO))
            .andExpect(jsonPath("$.bankAccountName").value(DEFAULT_BANK_ACCOUNT_NAME))
            .andExpect(jsonPath("$.bankBranch").value(DEFAULT_BANK_BRANCH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingFundRaiserBank() throws Exception {
        // Get the fundRaiserBank
        restFundRaiserBankMockMvc.perform(get("/api/fund-raiser-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFundRaiserBank() throws Exception {
        // Initialize the database
        fundRaiserBankRepository.saveAndFlush(fundRaiserBank);

        int databaseSizeBeforeUpdate = fundRaiserBankRepository.findAll().size();

        // Update the fundRaiserBank
        FundRaiserBank updatedFundRaiserBank = fundRaiserBankRepository.findById(fundRaiserBank.getId()).get();
        // Disconnect from session so that the updates on updatedFundRaiserBank are not directly saved in db
        em.detach(updatedFundRaiserBank);
        updatedFundRaiserBank
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS);
        FundRaiserBankDTO fundRaiserBankDTO = fundRaiserBankMapper.toDto(updatedFundRaiserBank);

        restFundRaiserBankMockMvc.perform(put("/api/fund-raiser-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserBankDTO)))
            .andExpect(status().isOk());

        // Validate the FundRaiserBank in the database
        List<FundRaiserBank> fundRaiserBankList = fundRaiserBankRepository.findAll();
        assertThat(fundRaiserBankList).hasSize(databaseSizeBeforeUpdate);
        FundRaiserBank testFundRaiserBank = fundRaiserBankList.get(fundRaiserBankList.size() - 1);
        assertThat(testFundRaiserBank.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testFundRaiserBank.getBankAccountName()).isEqualTo(UPDATED_BANK_ACCOUNT_NAME);
        assertThat(testFundRaiserBank.getBankBranch()).isEqualTo(UPDATED_BANK_BRANCH);
        assertThat(testFundRaiserBank.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingFundRaiserBank() throws Exception {
        int databaseSizeBeforeUpdate = fundRaiserBankRepository.findAll().size();

        // Create the FundRaiserBank
        FundRaiserBankDTO fundRaiserBankDTO = fundRaiserBankMapper.toDto(fundRaiserBank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundRaiserBankMockMvc.perform(put("/api/fund-raiser-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fundRaiserBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FundRaiserBank in the database
        List<FundRaiserBank> fundRaiserBankList = fundRaiserBankRepository.findAll();
        assertThat(fundRaiserBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFundRaiserBank() throws Exception {
        // Initialize the database
        fundRaiserBankRepository.saveAndFlush(fundRaiserBank);

        int databaseSizeBeforeDelete = fundRaiserBankRepository.findAll().size();

        // Delete the fundRaiserBank
        restFundRaiserBankMockMvc.perform(delete("/api/fund-raiser-banks/{id}", fundRaiserBank.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FundRaiserBank> fundRaiserBankList = fundRaiserBankRepository.findAll();
        assertThat(fundRaiserBankList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundRaiserBank.class);
        FundRaiserBank fundRaiserBank1 = new FundRaiserBank();
        fundRaiserBank1.setId(1L);
        FundRaiserBank fundRaiserBank2 = new FundRaiserBank();
        fundRaiserBank2.setId(fundRaiserBank1.getId());
        assertThat(fundRaiserBank1).isEqualTo(fundRaiserBank2);
        fundRaiserBank2.setId(2L);
        assertThat(fundRaiserBank1).isNotEqualTo(fundRaiserBank2);
        fundRaiserBank1.setId(null);
        assertThat(fundRaiserBank1).isNotEqualTo(fundRaiserBank2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundRaiserBankDTO.class);
        FundRaiserBankDTO fundRaiserBankDTO1 = new FundRaiserBankDTO();
        fundRaiserBankDTO1.setId(1L);
        FundRaiserBankDTO fundRaiserBankDTO2 = new FundRaiserBankDTO();
        assertThat(fundRaiserBankDTO1).isNotEqualTo(fundRaiserBankDTO2);
        fundRaiserBankDTO2.setId(fundRaiserBankDTO1.getId());
        assertThat(fundRaiserBankDTO1).isEqualTo(fundRaiserBankDTO2);
        fundRaiserBankDTO2.setId(2L);
        assertThat(fundRaiserBankDTO1).isNotEqualTo(fundRaiserBankDTO2);
        fundRaiserBankDTO1.setId(null);
        assertThat(fundRaiserBankDTO1).isNotEqualTo(fundRaiserBankDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fundRaiserBankMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fundRaiserBankMapper.fromId(null)).isNull();
    }
}
