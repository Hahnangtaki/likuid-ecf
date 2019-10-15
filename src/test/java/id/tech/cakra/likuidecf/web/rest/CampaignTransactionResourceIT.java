package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.CampaignTransaction;
import id.tech.cakra.likuidecf.repository.CampaignTransactionRepository;
import id.tech.cakra.likuidecf.service.CampaignTransactionService;
import id.tech.cakra.likuidecf.service.dto.CampaignTransactionDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignTransactionMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static id.tech.cakra.likuidecf.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CampaignTransactionResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class CampaignTransactionResourceIT {

    private static final String DEFAULT_TRANS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TRANS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANS_DESC = "AAAAAAAAAA";
    private static final String UPDATED_TRANS_DESC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TRANS_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRANS_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TRANS_TYPE = "A";
    private static final String UPDATED_TRANS_TYPE = "B";

    private static final Long DEFAULT_QTY = 1L;
    private static final Long UPDATED_QTY = 2L;

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final Double DEFAULT_UNIQUE_CHARGES = 1D;
    private static final Double UPDATED_UNIQUE_CHARGES = 2D;

    private static final Double DEFAULT_NET_AMOUNT = 1D;
    private static final Double UPDATED_NET_AMOUNT = 2D;

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    @Autowired
    private CampaignTransactionRepository campaignTransactionRepository;

    @Autowired
    private CampaignTransactionMapper campaignTransactionMapper;

    @Autowired
    private CampaignTransactionService campaignTransactionService;

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

    private MockMvc restCampaignTransactionMockMvc;

    private CampaignTransaction campaignTransaction;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignTransactionResource campaignTransactionResource = new CampaignTransactionResource(campaignTransactionService);
        this.restCampaignTransactionMockMvc = MockMvcBuilders.standaloneSetup(campaignTransactionResource)
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
    public static CampaignTransaction createEntity(EntityManager em) {
        CampaignTransaction campaignTransaction = new CampaignTransaction()
            .transCode(DEFAULT_TRANS_CODE)
            .transDesc(DEFAULT_TRANS_DESC)
            .transDate(DEFAULT_TRANS_DATE)
            .transType(DEFAULT_TRANS_TYPE)
            .qty(DEFAULT_QTY)
            .amount(DEFAULT_AMOUNT)
            .uniqueCharges(DEFAULT_UNIQUE_CHARGES)
            .netAmount(DEFAULT_NET_AMOUNT)
            .status(DEFAULT_STATUS);
        return campaignTransaction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignTransaction createUpdatedEntity(EntityManager em) {
        CampaignTransaction campaignTransaction = new CampaignTransaction()
            .transCode(UPDATED_TRANS_CODE)
            .transDesc(UPDATED_TRANS_DESC)
            .transDate(UPDATED_TRANS_DATE)
            .transType(UPDATED_TRANS_TYPE)
            .qty(UPDATED_QTY)
            .amount(UPDATED_AMOUNT)
            .uniqueCharges(UPDATED_UNIQUE_CHARGES)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS);
        return campaignTransaction;
    }

    @BeforeEach
    public void initTest() {
        campaignTransaction = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignTransaction() throws Exception {
        int databaseSizeBeforeCreate = campaignTransactionRepository.findAll().size();

        // Create the CampaignTransaction
        CampaignTransactionDTO campaignTransactionDTO = campaignTransactionMapper.toDto(campaignTransaction);
        restCampaignTransactionMockMvc.perform(post("/api/campaign-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignTransactionDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignTransaction in the database
        List<CampaignTransaction> campaignTransactionList = campaignTransactionRepository.findAll();
        assertThat(campaignTransactionList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignTransaction testCampaignTransaction = campaignTransactionList.get(campaignTransactionList.size() - 1);
        assertThat(testCampaignTransaction.getTransCode()).isEqualTo(DEFAULT_TRANS_CODE);
        assertThat(testCampaignTransaction.getTransDesc()).isEqualTo(DEFAULT_TRANS_DESC);
        assertThat(testCampaignTransaction.getTransDate()).isEqualTo(DEFAULT_TRANS_DATE);
        assertThat(testCampaignTransaction.getTransType()).isEqualTo(DEFAULT_TRANS_TYPE);
        assertThat(testCampaignTransaction.getQty()).isEqualTo(DEFAULT_QTY);
        assertThat(testCampaignTransaction.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCampaignTransaction.getUniqueCharges()).isEqualTo(DEFAULT_UNIQUE_CHARGES);
        assertThat(testCampaignTransaction.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testCampaignTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCampaignTransactionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignTransactionRepository.findAll().size();

        // Create the CampaignTransaction with an existing ID
        campaignTransaction.setId(1L);
        CampaignTransactionDTO campaignTransactionDTO = campaignTransactionMapper.toDto(campaignTransaction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignTransactionMockMvc.perform(post("/api/campaign-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignTransaction in the database
        List<CampaignTransaction> campaignTransactionList = campaignTransactionRepository.findAll();
        assertThat(campaignTransactionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignTransactions() throws Exception {
        // Initialize the database
        campaignTransactionRepository.saveAndFlush(campaignTransaction);

        // Get all the campaignTransactionList
        restCampaignTransactionMockMvc.perform(get("/api/campaign-transactions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignTransaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].transCode").value(hasItem(DEFAULT_TRANS_CODE)))
            .andExpect(jsonPath("$.[*].transDesc").value(hasItem(DEFAULT_TRANS_DESC)))
            .andExpect(jsonPath("$.[*].transDate").value(hasItem(DEFAULT_TRANS_DATE.toString())))
            .andExpect(jsonPath("$.[*].transType").value(hasItem(DEFAULT_TRANS_TYPE)))
            .andExpect(jsonPath("$.[*].qty").value(hasItem(DEFAULT_QTY.intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].uniqueCharges").value(hasItem(DEFAULT_UNIQUE_CHARGES.doubleValue())))
            .andExpect(jsonPath("$.[*].netAmount").value(hasItem(DEFAULT_NET_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getCampaignTransaction() throws Exception {
        // Initialize the database
        campaignTransactionRepository.saveAndFlush(campaignTransaction);

        // Get the campaignTransaction
        restCampaignTransactionMockMvc.perform(get("/api/campaign-transactions/{id}", campaignTransaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignTransaction.getId().intValue()))
            .andExpect(jsonPath("$.transCode").value(DEFAULT_TRANS_CODE))
            .andExpect(jsonPath("$.transDesc").value(DEFAULT_TRANS_DESC))
            .andExpect(jsonPath("$.transDate").value(DEFAULT_TRANS_DATE.toString()))
            .andExpect(jsonPath("$.transType").value(DEFAULT_TRANS_TYPE))
            .andExpect(jsonPath("$.qty").value(DEFAULT_QTY.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.uniqueCharges").value(DEFAULT_UNIQUE_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.netAmount").value(DEFAULT_NET_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignTransaction() throws Exception {
        // Get the campaignTransaction
        restCampaignTransactionMockMvc.perform(get("/api/campaign-transactions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignTransaction() throws Exception {
        // Initialize the database
        campaignTransactionRepository.saveAndFlush(campaignTransaction);

        int databaseSizeBeforeUpdate = campaignTransactionRepository.findAll().size();

        // Update the campaignTransaction
        CampaignTransaction updatedCampaignTransaction = campaignTransactionRepository.findById(campaignTransaction.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignTransaction are not directly saved in db
        em.detach(updatedCampaignTransaction);
        updatedCampaignTransaction
            .transCode(UPDATED_TRANS_CODE)
            .transDesc(UPDATED_TRANS_DESC)
            .transDate(UPDATED_TRANS_DATE)
            .transType(UPDATED_TRANS_TYPE)
            .qty(UPDATED_QTY)
            .amount(UPDATED_AMOUNT)
            .uniqueCharges(UPDATED_UNIQUE_CHARGES)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS);
        CampaignTransactionDTO campaignTransactionDTO = campaignTransactionMapper.toDto(updatedCampaignTransaction);

        restCampaignTransactionMockMvc.perform(put("/api/campaign-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignTransactionDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignTransaction in the database
        List<CampaignTransaction> campaignTransactionList = campaignTransactionRepository.findAll();
        assertThat(campaignTransactionList).hasSize(databaseSizeBeforeUpdate);
        CampaignTransaction testCampaignTransaction = campaignTransactionList.get(campaignTransactionList.size() - 1);
        assertThat(testCampaignTransaction.getTransCode()).isEqualTo(UPDATED_TRANS_CODE);
        assertThat(testCampaignTransaction.getTransDesc()).isEqualTo(UPDATED_TRANS_DESC);
        assertThat(testCampaignTransaction.getTransDate()).isEqualTo(UPDATED_TRANS_DATE);
        assertThat(testCampaignTransaction.getTransType()).isEqualTo(UPDATED_TRANS_TYPE);
        assertThat(testCampaignTransaction.getQty()).isEqualTo(UPDATED_QTY);
        assertThat(testCampaignTransaction.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCampaignTransaction.getUniqueCharges()).isEqualTo(UPDATED_UNIQUE_CHARGES);
        assertThat(testCampaignTransaction.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testCampaignTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignTransaction() throws Exception {
        int databaseSizeBeforeUpdate = campaignTransactionRepository.findAll().size();

        // Create the CampaignTransaction
        CampaignTransactionDTO campaignTransactionDTO = campaignTransactionMapper.toDto(campaignTransaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignTransactionMockMvc.perform(put("/api/campaign-transactions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignTransactionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignTransaction in the database
        List<CampaignTransaction> campaignTransactionList = campaignTransactionRepository.findAll();
        assertThat(campaignTransactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignTransaction() throws Exception {
        // Initialize the database
        campaignTransactionRepository.saveAndFlush(campaignTransaction);

        int databaseSizeBeforeDelete = campaignTransactionRepository.findAll().size();

        // Delete the campaignTransaction
        restCampaignTransactionMockMvc.perform(delete("/api/campaign-transactions/{id}", campaignTransaction.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignTransaction> campaignTransactionList = campaignTransactionRepository.findAll();
        assertThat(campaignTransactionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignTransaction.class);
        CampaignTransaction campaignTransaction1 = new CampaignTransaction();
        campaignTransaction1.setId(1L);
        CampaignTransaction campaignTransaction2 = new CampaignTransaction();
        campaignTransaction2.setId(campaignTransaction1.getId());
        assertThat(campaignTransaction1).isEqualTo(campaignTransaction2);
        campaignTransaction2.setId(2L);
        assertThat(campaignTransaction1).isNotEqualTo(campaignTransaction2);
        campaignTransaction1.setId(null);
        assertThat(campaignTransaction1).isNotEqualTo(campaignTransaction2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignTransactionDTO.class);
        CampaignTransactionDTO campaignTransactionDTO1 = new CampaignTransactionDTO();
        campaignTransactionDTO1.setId(1L);
        CampaignTransactionDTO campaignTransactionDTO2 = new CampaignTransactionDTO();
        assertThat(campaignTransactionDTO1).isNotEqualTo(campaignTransactionDTO2);
        campaignTransactionDTO2.setId(campaignTransactionDTO1.getId());
        assertThat(campaignTransactionDTO1).isEqualTo(campaignTransactionDTO2);
        campaignTransactionDTO2.setId(2L);
        assertThat(campaignTransactionDTO1).isNotEqualTo(campaignTransactionDTO2);
        campaignTransactionDTO1.setId(null);
        assertThat(campaignTransactionDTO1).isNotEqualTo(campaignTransactionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campaignTransactionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campaignTransactionMapper.fromId(null)).isNull();
    }
}
