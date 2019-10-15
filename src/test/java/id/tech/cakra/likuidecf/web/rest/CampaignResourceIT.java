package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.Campaign;
import id.tech.cakra.likuidecf.repository.CampaignRepository;
import id.tech.cakra.likuidecf.service.CampaignService;
import id.tech.cakra.likuidecf.service.dto.CampaignDTO;
import id.tech.cakra.likuidecf.service.mapper.CampaignMapper;
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
import org.springframework.util.Base64Utils;
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
 * Integration tests for the {@link CampaignResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class CampaignResourceIT {

    private static final String DEFAULT_CAMPAIGN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPAIGN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPAIGN_DESC = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OFFER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OFFER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DEADLINE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEADLINE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_FUND_TARGET = 1D;
    private static final Double UPDATED_FUND_TARGET = 2D;

    private static final Double DEFAULT_FUND_RAISED = 1D;
    private static final Double UPDATED_FUND_RAISED = 2D;

    private static final Double DEFAULT_FUND_PAID = 1D;
    private static final Double UPDATED_FUND_PAID = 2D;

    private static final Long DEFAULT_QTY_TARGET = 1L;
    private static final Long UPDATED_QTY_TARGET = 2L;

    private static final Long DEFAULT_QTY_RAISED = 1L;
    private static final Long UPDATED_QTY_RAISED = 2L;

    private static final Double DEFAULT_EST_ROI_MIN = 1D;
    private static final Double UPDATED_EST_ROI_MIN = 2D;

    private static final Double DEFAULT_EST_ROI_MAX = 1D;
    private static final Double UPDATED_EST_ROI_MAX = 2D;

    private static final byte[] DEFAULT_PROSPECTUS_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PROSPECTUS_FILE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PROSPECTUS_FILE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    @Autowired
    private CampaignService campaignService;

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

    private MockMvc restCampaignMockMvc;

    private Campaign campaign;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampaignResource campaignResource = new CampaignResource(campaignService);
        this.restCampaignMockMvc = MockMvcBuilders.standaloneSetup(campaignResource)
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
    public static Campaign createEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .campaignCode(DEFAULT_CAMPAIGN_CODE)
            .campaignName(DEFAULT_CAMPAIGN_NAME)
            .campaignDesc(DEFAULT_CAMPAIGN_DESC)
            .investmentType(DEFAULT_INVESTMENT_TYPE)
            .offerDate(DEFAULT_OFFER_DATE)
            .deadlineDate(DEFAULT_DEADLINE_DATE)
            .price(DEFAULT_PRICE)
            .fundTarget(DEFAULT_FUND_TARGET)
            .fundRaised(DEFAULT_FUND_RAISED)
            .fundPaid(DEFAULT_FUND_PAID)
            .qtyTarget(DEFAULT_QTY_TARGET)
            .qtyRaised(DEFAULT_QTY_RAISED)
            .estRoiMin(DEFAULT_EST_ROI_MIN)
            .estRoiMax(DEFAULT_EST_ROI_MAX)
            .prospectusFile(DEFAULT_PROSPECTUS_FILE)
            .prospectusFileContentType(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE)
            .status(DEFAULT_STATUS);
        return campaign;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createUpdatedEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .campaignCode(UPDATED_CAMPAIGN_CODE)
            .campaignName(UPDATED_CAMPAIGN_NAME)
            .campaignDesc(UPDATED_CAMPAIGN_DESC)
            .investmentType(UPDATED_INVESTMENT_TYPE)
            .offerDate(UPDATED_OFFER_DATE)
            .deadlineDate(UPDATED_DEADLINE_DATE)
            .price(UPDATED_PRICE)
            .fundTarget(UPDATED_FUND_TARGET)
            .fundRaised(UPDATED_FUND_RAISED)
            .fundPaid(UPDATED_FUND_PAID)
            .qtyTarget(UPDATED_QTY_TARGET)
            .qtyRaised(UPDATED_QTY_RAISED)
            .estRoiMin(UPDATED_EST_ROI_MIN)
            .estRoiMax(UPDATED_EST_ROI_MAX)
            .prospectusFile(UPDATED_PROSPECTUS_FILE)
            .prospectusFileContentType(UPDATED_PROSPECTUS_FILE_CONTENT_TYPE)
            .status(UPDATED_STATUS);
        return campaign;
    }

    @BeforeEach
    public void initTest() {
        campaign = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaign() throws Exception {
        int databaseSizeBeforeCreate = campaignRepository.findAll().size();

        // Create the Campaign
        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);
        restCampaignMockMvc.perform(post("/api/campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isCreated());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate + 1);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getCampaignCode()).isEqualTo(DEFAULT_CAMPAIGN_CODE);
        assertThat(testCampaign.getCampaignName()).isEqualTo(DEFAULT_CAMPAIGN_NAME);
        assertThat(testCampaign.getCampaignDesc()).isEqualTo(DEFAULT_CAMPAIGN_DESC);
        assertThat(testCampaign.getInvestmentType()).isEqualTo(DEFAULT_INVESTMENT_TYPE);
        assertThat(testCampaign.getOfferDate()).isEqualTo(DEFAULT_OFFER_DATE);
        assertThat(testCampaign.getDeadlineDate()).isEqualTo(DEFAULT_DEADLINE_DATE);
        assertThat(testCampaign.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testCampaign.getFundTarget()).isEqualTo(DEFAULT_FUND_TARGET);
        assertThat(testCampaign.getFundRaised()).isEqualTo(DEFAULT_FUND_RAISED);
        assertThat(testCampaign.getFundPaid()).isEqualTo(DEFAULT_FUND_PAID);
        assertThat(testCampaign.getQtyTarget()).isEqualTo(DEFAULT_QTY_TARGET);
        assertThat(testCampaign.getQtyRaised()).isEqualTo(DEFAULT_QTY_RAISED);
        assertThat(testCampaign.getEstRoiMin()).isEqualTo(DEFAULT_EST_ROI_MIN);
        assertThat(testCampaign.getEstRoiMax()).isEqualTo(DEFAULT_EST_ROI_MAX);
        assertThat(testCampaign.getProspectusFile()).isEqualTo(DEFAULT_PROSPECTUS_FILE);
        assertThat(testCampaign.getProspectusFileContentType()).isEqualTo(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE);
        assertThat(testCampaign.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCampaignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignRepository.findAll().size();

        // Create the Campaign with an existing ID
        campaign.setId(1L);
        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignMockMvc.perform(post("/api/campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampaigns() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get all the campaignList
        restCampaignMockMvc.perform(get("/api/campaigns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaign.getId().intValue())))
            .andExpect(jsonPath("$.[*].campaignCode").value(hasItem(DEFAULT_CAMPAIGN_CODE)))
            .andExpect(jsonPath("$.[*].campaignName").value(hasItem(DEFAULT_CAMPAIGN_NAME)))
            .andExpect(jsonPath("$.[*].campaignDesc").value(hasItem(DEFAULT_CAMPAIGN_DESC)))
            .andExpect(jsonPath("$.[*].investmentType").value(hasItem(DEFAULT_INVESTMENT_TYPE)))
            .andExpect(jsonPath("$.[*].offerDate").value(hasItem(DEFAULT_OFFER_DATE.toString())))
            .andExpect(jsonPath("$.[*].deadlineDate").value(hasItem(DEFAULT_DEADLINE_DATE.toString())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].fundTarget").value(hasItem(DEFAULT_FUND_TARGET.doubleValue())))
            .andExpect(jsonPath("$.[*].fundRaised").value(hasItem(DEFAULT_FUND_RAISED.doubleValue())))
            .andExpect(jsonPath("$.[*].fundPaid").value(hasItem(DEFAULT_FUND_PAID.doubleValue())))
            .andExpect(jsonPath("$.[*].qtyTarget").value(hasItem(DEFAULT_QTY_TARGET.intValue())))
            .andExpect(jsonPath("$.[*].qtyRaised").value(hasItem(DEFAULT_QTY_RAISED.intValue())))
            .andExpect(jsonPath("$.[*].estRoiMin").value(hasItem(DEFAULT_EST_ROI_MIN.doubleValue())))
            .andExpect(jsonPath("$.[*].estRoiMax").value(hasItem(DEFAULT_EST_ROI_MAX.doubleValue())))
            .andExpect(jsonPath("$.[*].prospectusFileContentType").value(hasItem(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].prospectusFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_PROSPECTUS_FILE))))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }
    
    @Test
    @Transactional
    public void getCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get the campaign
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", campaign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campaign.getId().intValue()))
            .andExpect(jsonPath("$.campaignCode").value(DEFAULT_CAMPAIGN_CODE))
            .andExpect(jsonPath("$.campaignName").value(DEFAULT_CAMPAIGN_NAME))
            .andExpect(jsonPath("$.campaignDesc").value(DEFAULT_CAMPAIGN_DESC))
            .andExpect(jsonPath("$.investmentType").value(DEFAULT_INVESTMENT_TYPE))
            .andExpect(jsonPath("$.offerDate").value(DEFAULT_OFFER_DATE.toString()))
            .andExpect(jsonPath("$.deadlineDate").value(DEFAULT_DEADLINE_DATE.toString()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.fundTarget").value(DEFAULT_FUND_TARGET.doubleValue()))
            .andExpect(jsonPath("$.fundRaised").value(DEFAULT_FUND_RAISED.doubleValue()))
            .andExpect(jsonPath("$.fundPaid").value(DEFAULT_FUND_PAID.doubleValue()))
            .andExpect(jsonPath("$.qtyTarget").value(DEFAULT_QTY_TARGET.intValue()))
            .andExpect(jsonPath("$.qtyRaised").value(DEFAULT_QTY_RAISED.intValue()))
            .andExpect(jsonPath("$.estRoiMin").value(DEFAULT_EST_ROI_MIN.doubleValue()))
            .andExpect(jsonPath("$.estRoiMax").value(DEFAULT_EST_ROI_MAX.doubleValue()))
            .andExpect(jsonPath("$.prospectusFileContentType").value(DEFAULT_PROSPECTUS_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.prospectusFile").value(Base64Utils.encodeToString(DEFAULT_PROSPECTUS_FILE)))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingCampaign() throws Exception {
        // Get the campaign
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        int databaseSizeBeforeUpdate = campaignRepository.findAll().size();

        // Update the campaign
        Campaign updatedCampaign = campaignRepository.findById(campaign.getId()).get();
        // Disconnect from session so that the updates on updatedCampaign are not directly saved in db
        em.detach(updatedCampaign);
        updatedCampaign
            .campaignCode(UPDATED_CAMPAIGN_CODE)
            .campaignName(UPDATED_CAMPAIGN_NAME)
            .campaignDesc(UPDATED_CAMPAIGN_DESC)
            .investmentType(UPDATED_INVESTMENT_TYPE)
            .offerDate(UPDATED_OFFER_DATE)
            .deadlineDate(UPDATED_DEADLINE_DATE)
            .price(UPDATED_PRICE)
            .fundTarget(UPDATED_FUND_TARGET)
            .fundRaised(UPDATED_FUND_RAISED)
            .fundPaid(UPDATED_FUND_PAID)
            .qtyTarget(UPDATED_QTY_TARGET)
            .qtyRaised(UPDATED_QTY_RAISED)
            .estRoiMin(UPDATED_EST_ROI_MIN)
            .estRoiMax(UPDATED_EST_ROI_MAX)
            .prospectusFile(UPDATED_PROSPECTUS_FILE)
            .prospectusFileContentType(UPDATED_PROSPECTUS_FILE_CONTENT_TYPE)
            .status(UPDATED_STATUS);
        CampaignDTO campaignDTO = campaignMapper.toDto(updatedCampaign);

        restCampaignMockMvc.perform(put("/api/campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isOk());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getCampaignCode()).isEqualTo(UPDATED_CAMPAIGN_CODE);
        assertThat(testCampaign.getCampaignName()).isEqualTo(UPDATED_CAMPAIGN_NAME);
        assertThat(testCampaign.getCampaignDesc()).isEqualTo(UPDATED_CAMPAIGN_DESC);
        assertThat(testCampaign.getInvestmentType()).isEqualTo(UPDATED_INVESTMENT_TYPE);
        assertThat(testCampaign.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testCampaign.getDeadlineDate()).isEqualTo(UPDATED_DEADLINE_DATE);
        assertThat(testCampaign.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testCampaign.getFundTarget()).isEqualTo(UPDATED_FUND_TARGET);
        assertThat(testCampaign.getFundRaised()).isEqualTo(UPDATED_FUND_RAISED);
        assertThat(testCampaign.getFundPaid()).isEqualTo(UPDATED_FUND_PAID);
        assertThat(testCampaign.getQtyTarget()).isEqualTo(UPDATED_QTY_TARGET);
        assertThat(testCampaign.getQtyRaised()).isEqualTo(UPDATED_QTY_RAISED);
        assertThat(testCampaign.getEstRoiMin()).isEqualTo(UPDATED_EST_ROI_MIN);
        assertThat(testCampaign.getEstRoiMax()).isEqualTo(UPDATED_EST_ROI_MAX);
        assertThat(testCampaign.getProspectusFile()).isEqualTo(UPDATED_PROSPECTUS_FILE);
        assertThat(testCampaign.getProspectusFileContentType()).isEqualTo(UPDATED_PROSPECTUS_FILE_CONTENT_TYPE);
        assertThat(testCampaign.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().size();

        // Create the Campaign
        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignMockMvc.perform(put("/api/campaigns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        int databaseSizeBeforeDelete = campaignRepository.findAll().size();

        // Delete the campaign
        restCampaignMockMvc.perform(delete("/api/campaigns/{id}", campaign.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Campaign.class);
        Campaign campaign1 = new Campaign();
        campaign1.setId(1L);
        Campaign campaign2 = new Campaign();
        campaign2.setId(campaign1.getId());
        assertThat(campaign1).isEqualTo(campaign2);
        campaign2.setId(2L);
        assertThat(campaign1).isNotEqualTo(campaign2);
        campaign1.setId(null);
        assertThat(campaign1).isNotEqualTo(campaign2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignDTO.class);
        CampaignDTO campaignDTO1 = new CampaignDTO();
        campaignDTO1.setId(1L);
        CampaignDTO campaignDTO2 = new CampaignDTO();
        assertThat(campaignDTO1).isNotEqualTo(campaignDTO2);
        campaignDTO2.setId(campaignDTO1.getId());
        assertThat(campaignDTO1).isEqualTo(campaignDTO2);
        campaignDTO2.setId(2L);
        assertThat(campaignDTO1).isNotEqualTo(campaignDTO2);
        campaignDTO1.setId(null);
        assertThat(campaignDTO1).isNotEqualTo(campaignDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campaignMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campaignMapper.fromId(null)).isNull();
    }
}
