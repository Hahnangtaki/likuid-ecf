package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.InvestorBank;
import id.tech.cakra.likuidecf.repository.InvestorBankRepository;
import id.tech.cakra.likuidecf.service.InvestorBankService;
import id.tech.cakra.likuidecf.service.dto.InvestorBankDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorBankMapper;
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
 * Integration tests for the {@link InvestorBankResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class InvestorBankResourceIT {

    private static final String DEFAULT_BANK_ACCOUNT_NO = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BANK_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BANK_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    @Autowired
    private InvestorBankRepository investorBankRepository;

    @Autowired
    private InvestorBankMapper investorBankMapper;

    @Autowired
    private InvestorBankService investorBankService;

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

    private MockMvc restInvestorBankMockMvc;

    private InvestorBank investorBank;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorBankResource investorBankResource = new InvestorBankResource(investorBankService);
        this.restInvestorBankMockMvc = MockMvcBuilders.standaloneSetup(investorBankResource)
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
    public static InvestorBank createEntity(EntityManager em) {
        InvestorBank investorBank = new InvestorBank()
            .bankAccountNo(DEFAULT_BANK_ACCOUNT_NO)
            .bankAccountName(DEFAULT_BANK_ACCOUNT_NAME)
            .bankBranch(DEFAULT_BANK_BRANCH)
            .status(DEFAULT_STATUS);
        return investorBank;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvestorBank createUpdatedEntity(EntityManager em) {
        InvestorBank investorBank = new InvestorBank()
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS);
        return investorBank;
    }

    @BeforeEach
    public void initTest() {
        investorBank = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestorBank() throws Exception {
        int databaseSizeBeforeCreate = investorBankRepository.findAll().size();

        // Create the InvestorBank
        InvestorBankDTO investorBankDTO = investorBankMapper.toDto(investorBank);
        restInvestorBankMockMvc.perform(post("/api/investor-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorBankDTO)))
            .andExpect(status().isCreated());

        // Validate the InvestorBank in the database
        List<InvestorBank> investorBankList = investorBankRepository.findAll();
        assertThat(investorBankList).hasSize(databaseSizeBeforeCreate + 1);
        InvestorBank testInvestorBank = investorBankList.get(investorBankList.size() - 1);
        assertThat(testInvestorBank.getBankAccountNo()).isEqualTo(DEFAULT_BANK_ACCOUNT_NO);
        assertThat(testInvestorBank.getBankAccountName()).isEqualTo(DEFAULT_BANK_ACCOUNT_NAME);
        assertThat(testInvestorBank.getBankBranch()).isEqualTo(DEFAULT_BANK_BRANCH);
        assertThat(testInvestorBank.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createInvestorBankWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorBankRepository.findAll().size();

        // Create the InvestorBank with an existing ID
        investorBank.setId(1L);
        InvestorBankDTO investorBankDTO = investorBankMapper.toDto(investorBank);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorBankMockMvc.perform(post("/api/investor-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorBank in the database
        List<InvestorBank> investorBankList = investorBankRepository.findAll();
        assertThat(investorBankList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvestorBanks() throws Exception {
        // Initialize the database
        investorBankRepository.saveAndFlush(investorBank);

        // Get all the investorBankList
        restInvestorBankMockMvc.perform(get("/api/investor-banks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investorBank.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankAccountNo").value(hasItem(DEFAULT_BANK_ACCOUNT_NO)))
            .andExpect(jsonPath("$.[*].bankAccountName").value(hasItem(DEFAULT_BANK_ACCOUNT_NAME)))
            .andExpect(jsonPath("$.[*].bankBranch").value(hasItem(DEFAULT_BANK_BRANCH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getInvestorBank() throws Exception {
        // Initialize the database
        investorBankRepository.saveAndFlush(investorBank);

        // Get the investorBank
        restInvestorBankMockMvc.perform(get("/api/investor-banks/{id}", investorBank.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investorBank.getId().intValue()))
            .andExpect(jsonPath("$.bankAccountNo").value(DEFAULT_BANK_ACCOUNT_NO))
            .andExpect(jsonPath("$.bankAccountName").value(DEFAULT_BANK_ACCOUNT_NAME))
            .andExpect(jsonPath("$.bankBranch").value(DEFAULT_BANK_BRANCH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingInvestorBank() throws Exception {
        // Get the investorBank
        restInvestorBankMockMvc.perform(get("/api/investor-banks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestorBank() throws Exception {
        // Initialize the database
        investorBankRepository.saveAndFlush(investorBank);

        int databaseSizeBeforeUpdate = investorBankRepository.findAll().size();

        // Update the investorBank
        InvestorBank updatedInvestorBank = investorBankRepository.findById(investorBank.getId()).get();
        // Disconnect from session so that the updates on updatedInvestorBank are not directly saved in db
        em.detach(updatedInvestorBank);
        updatedInvestorBank
            .bankAccountNo(UPDATED_BANK_ACCOUNT_NO)
            .bankAccountName(UPDATED_BANK_ACCOUNT_NAME)
            .bankBranch(UPDATED_BANK_BRANCH)
            .status(UPDATED_STATUS);
        InvestorBankDTO investorBankDTO = investorBankMapper.toDto(updatedInvestorBank);

        restInvestorBankMockMvc.perform(put("/api/investor-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorBankDTO)))
            .andExpect(status().isOk());

        // Validate the InvestorBank in the database
        List<InvestorBank> investorBankList = investorBankRepository.findAll();
        assertThat(investorBankList).hasSize(databaseSizeBeforeUpdate);
        InvestorBank testInvestorBank = investorBankList.get(investorBankList.size() - 1);
        assertThat(testInvestorBank.getBankAccountNo()).isEqualTo(UPDATED_BANK_ACCOUNT_NO);
        assertThat(testInvestorBank.getBankAccountName()).isEqualTo(UPDATED_BANK_ACCOUNT_NAME);
        assertThat(testInvestorBank.getBankBranch()).isEqualTo(UPDATED_BANK_BRANCH);
        assertThat(testInvestorBank.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestorBank() throws Exception {
        int databaseSizeBeforeUpdate = investorBankRepository.findAll().size();

        // Create the InvestorBank
        InvestorBankDTO investorBankDTO = investorBankMapper.toDto(investorBank);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvestorBankMockMvc.perform(put("/api/investor-banks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorBankDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorBank in the database
        List<InvestorBank> investorBankList = investorBankRepository.findAll();
        assertThat(investorBankList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestorBank() throws Exception {
        // Initialize the database
        investorBankRepository.saveAndFlush(investorBank);

        int databaseSizeBeforeDelete = investorBankRepository.findAll().size();

        // Delete the investorBank
        restInvestorBankMockMvc.perform(delete("/api/investor-banks/{id}", investorBank.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvestorBank> investorBankList = investorBankRepository.findAll();
        assertThat(investorBankList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorBank.class);
        InvestorBank investorBank1 = new InvestorBank();
        investorBank1.setId(1L);
        InvestorBank investorBank2 = new InvestorBank();
        investorBank2.setId(investorBank1.getId());
        assertThat(investorBank1).isEqualTo(investorBank2);
        investorBank2.setId(2L);
        assertThat(investorBank1).isNotEqualTo(investorBank2);
        investorBank1.setId(null);
        assertThat(investorBank1).isNotEqualTo(investorBank2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorBankDTO.class);
        InvestorBankDTO investorBankDTO1 = new InvestorBankDTO();
        investorBankDTO1.setId(1L);
        InvestorBankDTO investorBankDTO2 = new InvestorBankDTO();
        assertThat(investorBankDTO1).isNotEqualTo(investorBankDTO2);
        investorBankDTO2.setId(investorBankDTO1.getId());
        assertThat(investorBankDTO1).isEqualTo(investorBankDTO2);
        investorBankDTO2.setId(2L);
        assertThat(investorBankDTO1).isNotEqualTo(investorBankDTO2);
        investorBankDTO1.setId(null);
        assertThat(investorBankDTO1).isNotEqualTo(investorBankDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorBankMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorBankMapper.fromId(null)).isNull();
    }
}
