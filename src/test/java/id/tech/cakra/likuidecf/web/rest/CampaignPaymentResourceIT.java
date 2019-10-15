package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.CampaignPayment;
import id.tech.cakra.likuidecf.repository.CampaignPaymentRepository;
import id.tech.cakra.likuidecf.service.CampaignPaymentService;
import id.tech.cakra.likuidecf.service.dto.CampaignPaymentDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignPaymentMapper;
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
 * Integration tests for the {@link CampaignPaymentResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class CampaignPaymentResourceIT {

    private static final String DEFAULT_PAYMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_DESC = "BBBBBBBBBB";

    private static final Double DEFAULT_AMOUNT = 1D;
    private static final Double UPDATED_AMOUNT = 2D;

    private static final String DEFAULT_STATUS = "A";
    private static final String UPDATED_STATUS = "B";

    @Autowired
    private CampaignPaymentRepository campaignPaymentRepository;

    @Autowired
    private CampaignPaymentMapper campaignPaymentMapper;

    @Autowired
    private CampaignPaymentService campaignPaymentService;

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

    private MockMvc restCampaignPaymentMockMvc;

    private CampaignPayment campaignPayment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignPaymentResource campaignPaymentResource = new CampaignPaymentResource(campaignPaymentService);
        this.restCampaignPaymentMockMvc = MockMvcBuilders.standaloneSetup(campaignPaymentResource)
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
    public static CampaignPayment createEntity(EntityManager em) {
        CampaignPayment campaignPayment = new CampaignPayment()
            .paymentCode(DEFAULT_PAYMENT_CODE)
            .paymentDesc(DEFAULT_PAYMENT_DESC)
            .amount(DEFAULT_AMOUNT)
            .status(DEFAULT_STATUS);
        return campaignPayment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampaignPayment createUpdatedEntity(EntityManager em) {
        CampaignPayment campaignPayment = new CampaignPayment()
            .paymentCode(UPDATED_PAYMENT_CODE)
            .paymentDesc(UPDATED_PAYMENT_DESC)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS);
        return campaignPayment;
    }

    @BeforeEach
    public void initTest() {
        campaignPayment = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaignPayment() throws Exception {
        int databaseSizeBeforeCreate = campaignPaymentRepository.findAll().size();

        // Create the CampaignPayment
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(campaignPayment);
        restCampaignPaymentMockMvc.perform(post("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isCreated());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeCreate + 1);
        CampaignPayment testCampaignPayment = campaignPaymentList.get(campaignPaymentList.size() - 1);
        assertThat(testCampaignPayment.getPaymentCode()).isEqualTo(DEFAULT_PAYMENT_CODE);
        assertThat(testCampaignPayment.getPaymentDesc()).isEqualTo(DEFAULT_PAYMENT_DESC);
        assertThat(testCampaignPayment.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCampaignPayment.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCampaignPaymentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignPaymentRepository.findAll().size();

        // Create the CampaignPayment with an existing ID
        campaignPayment.setId(1L);
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(campaignPayment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignPaymentMockMvc.perform(post("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaignPayments() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        // Get all the campaignPaymentList
        restCampaignPaymentMockMvc.perform(get("/api/campaign-payments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaignPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymentCode").value(hasItem(DEFAULT_PAYMENT_CODE)))
            .andExpect(jsonPath("$.[*].paymentDesc").value(hasItem(DEFAULT_PAYMENT_DESC)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getCampaignPayment() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        // Get the campaignPayment
        restCampaignPaymentMockMvc.perform(get("/api/campaign-payments/{id}", campaignPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaignPayment.getId().intValue()))
            .andExpect(jsonPath("$.paymentCode").value(DEFAULT_PAYMENT_CODE))
            .andExpect(jsonPath("$.paymentDesc").value(DEFAULT_PAYMENT_DESC))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingCampaignPayment() throws Exception {
        // Get the campaignPayment
        restCampaignPaymentMockMvc.perform(get("/api/campaign-payments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaignPayment() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        int databaseSizeBeforeUpdate = campaignPaymentRepository.findAll().size();

        // Update the campaignPayment
        CampaignPayment updatedCampaignPayment = campaignPaymentRepository.findById(campaignPayment.getId()).get();
        // Disconnect from session so that the updates on updatedCampaignPayment are not directly saved in db
        em.detach(updatedCampaignPayment);
        updatedCampaignPayment
            .paymentCode(UPDATED_PAYMENT_CODE)
            .paymentDesc(UPDATED_PAYMENT_DESC)
            .amount(UPDATED_AMOUNT)
            .status(UPDATED_STATUS);
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(updatedCampaignPayment);

        restCampaignPaymentMockMvc.perform(put("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isOk());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeUpdate);
        CampaignPayment testCampaignPayment = campaignPaymentList.get(campaignPaymentList.size() - 1);
        assertThat(testCampaignPayment.getPaymentCode()).isEqualTo(UPDATED_PAYMENT_CODE);
        assertThat(testCampaignPayment.getPaymentDesc()).isEqualTo(UPDATED_PAYMENT_DESC);
        assertThat(testCampaignPayment.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCampaignPayment.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaignPayment() throws Exception {
        int databaseSizeBeforeUpdate = campaignPaymentRepository.findAll().size();

        // Create the CampaignPayment
        CampaignPaymentDTO campaignPaymentDTO = campaignPaymentMapper.toDto(campaignPayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignPaymentMockMvc.perform(put("/api/campaign-payments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignPaymentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampaignPayment in the database
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaignPayment() throws Exception {
        // Initialize the database
        campaignPaymentRepository.saveAndFlush(campaignPayment);

        int databaseSizeBeforeDelete = campaignPaymentRepository.findAll().size();

        // Delete the campaignPayment
        restCampaignPaymentMockMvc.perform(delete("/api/campaign-payments/{id}", campaignPayment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampaignPayment> campaignPaymentList = campaignPaymentRepository.findAll();
        assertThat(campaignPaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignPayment.class);
        CampaignPayment campaignPayment1 = new CampaignPayment();
        campaignPayment1.setId(1L);
        CampaignPayment campaignPayment2 = new CampaignPayment();
        campaignPayment2.setId(campaignPayment1.getId());
        assertThat(campaignPayment1).isEqualTo(campaignPayment2);
        campaignPayment2.setId(2L);
        assertThat(campaignPayment1).isNotEqualTo(campaignPayment2);
        campaignPayment1.setId(null);
        assertThat(campaignPayment1).isNotEqualTo(campaignPayment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignPaymentDTO.class);
        CampaignPaymentDTO campaignPaymentDTO1 = new CampaignPaymentDTO();
        campaignPaymentDTO1.setId(1L);
        CampaignPaymentDTO campaignPaymentDTO2 = new CampaignPaymentDTO();
        assertThat(campaignPaymentDTO1).isNotEqualTo(campaignPaymentDTO2);
        campaignPaymentDTO2.setId(campaignPaymentDTO1.getId());
        assertThat(campaignPaymentDTO1).isEqualTo(campaignPaymentDTO2);
        campaignPaymentDTO2.setId(2L);
        assertThat(campaignPaymentDTO1).isNotEqualTo(campaignPaymentDTO2);
        campaignPaymentDTO1.setId(null);
        assertThat(campaignPaymentDTO1).isNotEqualTo(campaignPaymentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campaignPaymentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campaignPaymentMapper.fromId(null)).isNull();
    }
}
