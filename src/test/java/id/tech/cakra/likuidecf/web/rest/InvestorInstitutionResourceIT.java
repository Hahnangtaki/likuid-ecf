package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.InvestorInstitution;
import id.tech.cakra.likuidecf.repository.InvestorInstitutionRepository;
import id.tech.cakra.likuidecf.service.InvestorInstitutionService;
import id.tech.cakra.likuidecf.service.dto.InvestorInstitutionDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorInstitutionMapper;
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
 * Integration tests for the {@link InvestorInstitutionResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class InvestorInstitutionResourceIT {

    private static final String DEFAULT_SID = "AAAAAAAAAA";
    private static final String UPDATED_SID = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BIC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BIC_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_LEGAL_DOMICILE_ID = 1L;
    private static final Long UPDATED_LEGAL_DOMICILE_ID = 2L;

    private static final String DEFAULT_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_NPWP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NPWP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NPWP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SKD = "AAAAAAAAAA";
    private static final String UPDATED_SKD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_SKD_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SKD_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMPANY_ESTABLISH_PLCE = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_ESTABLISH_PLCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_COMPANY_ESTABLISH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPANY_ESTABLISH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BUSINESS_TYPE = "A";
    private static final String UPDATED_BUSINESS_TYPE = "B";

    private static final String DEFAULT_COMPANY_CHRACTERISTIC = "A";
    private static final String UPDATED_COMPANY_CHRACTERISTIC = "B";

    private static final String DEFAULT_FUND_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_SOURCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_ARTICLE_ASSOCIATION = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_ASSOCIATION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSSINESS_REG_NO = "AAAAAAAAAA";
    private static final String UPDATED_BUSSINESS_REG_NO = "BBBBBBBBBB";

    private static final Double DEFAULT_FINANCIAL_ASSET_1 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_1 = 2D;

    private static final Double DEFAULT_FINANCIAL_ASSET_2 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_2 = 2D;

    private static final Double DEFAULT_FINANCIAL_ASSET_3 = 1D;
    private static final Double UPDATED_FINANCIAL_ASSET_3 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_1 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_1 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_2 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_2 = 2D;

    private static final Double DEFAULT_OPERATING_PROFIT_3 = 1D;
    private static final Double UPDATED_OPERATING_PROFIT_3 = 2D;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECT_SID = "AAAAAAAAAA";
    private static final String UPDATED_DIRECT_SID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_OWNER = "A";
    private static final String UPDATED_ASSET_OWNER = "B";

    private static final String DEFAULT_SUP_DOC_TYPE = "A";
    private static final String UPDATED_SUP_DOC_TYPE = "B";

    private static final LocalDate DEFAULT_SUP_DOC_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUP_DOC_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private InvestorInstitutionRepository investorInstitutionRepository;

    @Autowired
    private InvestorInstitutionMapper investorInstitutionMapper;

    @Autowired
    private InvestorInstitutionService investorInstitutionService;

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

    private MockMvc restInvestorInstitutionMockMvc;

    private InvestorInstitution investorInstitution;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorInstitutionResource investorInstitutionResource = new InvestorInstitutionResource(investorInstitutionService);
        this.restInvestorInstitutionMockMvc = MockMvcBuilders.standaloneSetup(investorInstitutionResource)
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
    public static InvestorInstitution createEntity(EntityManager em) {
        InvestorInstitution investorInstitution = new InvestorInstitution()
            .sid(DEFAULT_SID)
            .companyName(DEFAULT_COMPANY_NAME)
            .bicCode(DEFAULT_BIC_CODE)
            .legalDomicileId(DEFAULT_LEGAL_DOMICILE_ID)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .skd(DEFAULT_SKD)
            .skdExpDate(DEFAULT_SKD_EXP_DATE)
            .companyEstablishPlce(DEFAULT_COMPANY_ESTABLISH_PLCE)
            .companyEstablishDate(DEFAULT_COMPANY_ESTABLISH_DATE)
            .businessType(DEFAULT_BUSINESS_TYPE)
            .companyChracteristic(DEFAULT_COMPANY_CHRACTERISTIC)
            .fundSource(DEFAULT_FUND_SOURCE)
            .fundSourceText(DEFAULT_FUND_SOURCE_TEXT)
            .articleAssociation(DEFAULT_ARTICLE_ASSOCIATION)
            .bussinessRegNo(DEFAULT_BUSSINESS_REG_NO)
            .financialAsset1(DEFAULT_FINANCIAL_ASSET_1)
            .financialAsset2(DEFAULT_FINANCIAL_ASSET_2)
            .financialAsset3(DEFAULT_FINANCIAL_ASSET_3)
            .operatingProfit1(DEFAULT_OPERATING_PROFIT_1)
            .operatingProfit2(DEFAULT_OPERATING_PROFIT_2)
            .operatingProfit3(DEFAULT_OPERATING_PROFIT_3)
            .description(DEFAULT_DESCRIPTION)
            .investmentObjective(DEFAULT_INVESTMENT_OBJECTIVE)
            .directSid(DEFAULT_DIRECT_SID)
            .assetOwner(DEFAULT_ASSET_OWNER)
            .supDocType(DEFAULT_SUP_DOC_TYPE)
            .supDocExpDate(DEFAULT_SUP_DOC_EXP_DATE);
        return investorInstitution;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvestorInstitution createUpdatedEntity(EntityManager em) {
        InvestorInstitution investorInstitution = new InvestorInstitution()
            .sid(UPDATED_SID)
            .companyName(UPDATED_COMPANY_NAME)
            .bicCode(UPDATED_BIC_CODE)
            .legalDomicileId(UPDATED_LEGAL_DOMICILE_ID)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .skd(UPDATED_SKD)
            .skdExpDate(UPDATED_SKD_EXP_DATE)
            .companyEstablishPlce(UPDATED_COMPANY_ESTABLISH_PLCE)
            .companyEstablishDate(UPDATED_COMPANY_ESTABLISH_DATE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .companyChracteristic(UPDATED_COMPANY_CHRACTERISTIC)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .articleAssociation(UPDATED_ARTICLE_ASSOCIATION)
            .bussinessRegNo(UPDATED_BUSSINESS_REG_NO)
            .financialAsset1(UPDATED_FINANCIAL_ASSET_1)
            .financialAsset2(UPDATED_FINANCIAL_ASSET_2)
            .financialAsset3(UPDATED_FINANCIAL_ASSET_3)
            .operatingProfit1(UPDATED_OPERATING_PROFIT_1)
            .operatingProfit2(UPDATED_OPERATING_PROFIT_2)
            .operatingProfit3(UPDATED_OPERATING_PROFIT_3)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .supDocType(UPDATED_SUP_DOC_TYPE)
            .supDocExpDate(UPDATED_SUP_DOC_EXP_DATE);
        return investorInstitution;
    }

    @BeforeEach
    public void initTest() {
        investorInstitution = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestorInstitution() throws Exception {
        int databaseSizeBeforeCreate = investorInstitutionRepository.findAll().size();

        // Create the InvestorInstitution
        InvestorInstitutionDTO investorInstitutionDTO = investorInstitutionMapper.toDto(investorInstitution);
        restInvestorInstitutionMockMvc.perform(post("/api/investor-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorInstitutionDTO)))
            .andExpect(status().isCreated());

        // Validate the InvestorInstitution in the database
        List<InvestorInstitution> investorInstitutionList = investorInstitutionRepository.findAll();
        assertThat(investorInstitutionList).hasSize(databaseSizeBeforeCreate + 1);
        InvestorInstitution testInvestorInstitution = investorInstitutionList.get(investorInstitutionList.size() - 1);
        assertThat(testInvestorInstitution.getSid()).isEqualTo(DEFAULT_SID);
        assertThat(testInvestorInstitution.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testInvestorInstitution.getBicCode()).isEqualTo(DEFAULT_BIC_CODE);
        assertThat(testInvestorInstitution.getLegalDomicileId()).isEqualTo(DEFAULT_LEGAL_DOMICILE_ID);
        assertThat(testInvestorInstitution.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testInvestorInstitution.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testInvestorInstitution.getSkd()).isEqualTo(DEFAULT_SKD);
        assertThat(testInvestorInstitution.getSkdExpDate()).isEqualTo(DEFAULT_SKD_EXP_DATE);
        assertThat(testInvestorInstitution.getCompanyEstablishPlce()).isEqualTo(DEFAULT_COMPANY_ESTABLISH_PLCE);
        assertThat(testInvestorInstitution.getCompanyEstablishDate()).isEqualTo(DEFAULT_COMPANY_ESTABLISH_DATE);
        assertThat(testInvestorInstitution.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testInvestorInstitution.getCompanyChracteristic()).isEqualTo(DEFAULT_COMPANY_CHRACTERISTIC);
        assertThat(testInvestorInstitution.getFundSource()).isEqualTo(DEFAULT_FUND_SOURCE);
        assertThat(testInvestorInstitution.getFundSourceText()).isEqualTo(DEFAULT_FUND_SOURCE_TEXT);
        assertThat(testInvestorInstitution.getArticleAssociation()).isEqualTo(DEFAULT_ARTICLE_ASSOCIATION);
        assertThat(testInvestorInstitution.getBussinessRegNo()).isEqualTo(DEFAULT_BUSSINESS_REG_NO);
        assertThat(testInvestorInstitution.getFinancialAsset1()).isEqualTo(DEFAULT_FINANCIAL_ASSET_1);
        assertThat(testInvestorInstitution.getFinancialAsset2()).isEqualTo(DEFAULT_FINANCIAL_ASSET_2);
        assertThat(testInvestorInstitution.getFinancialAsset3()).isEqualTo(DEFAULT_FINANCIAL_ASSET_3);
        assertThat(testInvestorInstitution.getOperatingProfit1()).isEqualTo(DEFAULT_OPERATING_PROFIT_1);
        assertThat(testInvestorInstitution.getOperatingProfit2()).isEqualTo(DEFAULT_OPERATING_PROFIT_2);
        assertThat(testInvestorInstitution.getOperatingProfit3()).isEqualTo(DEFAULT_OPERATING_PROFIT_3);
        assertThat(testInvestorInstitution.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInvestorInstitution.getInvestmentObjective()).isEqualTo(DEFAULT_INVESTMENT_OBJECTIVE);
        assertThat(testInvestorInstitution.getDirectSid()).isEqualTo(DEFAULT_DIRECT_SID);
        assertThat(testInvestorInstitution.getAssetOwner()).isEqualTo(DEFAULT_ASSET_OWNER);
        assertThat(testInvestorInstitution.getSupDocType()).isEqualTo(DEFAULT_SUP_DOC_TYPE);
        assertThat(testInvestorInstitution.getSupDocExpDate()).isEqualTo(DEFAULT_SUP_DOC_EXP_DATE);
    }

    @Test
    @Transactional
    public void createInvestorInstitutionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorInstitutionRepository.findAll().size();

        // Create the InvestorInstitution with an existing ID
        investorInstitution.setId(1L);
        InvestorInstitutionDTO investorInstitutionDTO = investorInstitutionMapper.toDto(investorInstitution);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorInstitutionMockMvc.perform(post("/api/investor-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorInstitutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorInstitution in the database
        List<InvestorInstitution> investorInstitutionList = investorInstitutionRepository.findAll();
        assertThat(investorInstitutionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvestorInstitutions() throws Exception {
        // Initialize the database
        investorInstitutionRepository.saveAndFlush(investorInstitution);

        // Get all the investorInstitutionList
        restInvestorInstitutionMockMvc.perform(get("/api/investor-institutions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investorInstitution.getId().intValue())))
            .andExpect(jsonPath("$.[*].sid").value(hasItem(DEFAULT_SID)))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME)))
            .andExpect(jsonPath("$.[*].bicCode").value(hasItem(DEFAULT_BIC_CODE)))
            .andExpect(jsonPath("$.[*].legalDomicileId").value(hasItem(DEFAULT_LEGAL_DOMICILE_ID.intValue())))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].skd").value(hasItem(DEFAULT_SKD)))
            .andExpect(jsonPath("$.[*].skdExpDate").value(hasItem(DEFAULT_SKD_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].companyEstablishPlce").value(hasItem(DEFAULT_COMPANY_ESTABLISH_PLCE)))
            .andExpect(jsonPath("$.[*].companyEstablishDate").value(hasItem(DEFAULT_COMPANY_ESTABLISH_DATE.toString())))
            .andExpect(jsonPath("$.[*].businessType").value(hasItem(DEFAULT_BUSINESS_TYPE)))
            .andExpect(jsonPath("$.[*].companyChracteristic").value(hasItem(DEFAULT_COMPANY_CHRACTERISTIC)))
            .andExpect(jsonPath("$.[*].fundSource").value(hasItem(DEFAULT_FUND_SOURCE)))
            .andExpect(jsonPath("$.[*].fundSourceText").value(hasItem(DEFAULT_FUND_SOURCE_TEXT)))
            .andExpect(jsonPath("$.[*].articleAssociation").value(hasItem(DEFAULT_ARTICLE_ASSOCIATION)))
            .andExpect(jsonPath("$.[*].bussinessRegNo").value(hasItem(DEFAULT_BUSSINESS_REG_NO)))
            .andExpect(jsonPath("$.[*].financialAsset1").value(hasItem(DEFAULT_FINANCIAL_ASSET_1.doubleValue())))
            .andExpect(jsonPath("$.[*].financialAsset2").value(hasItem(DEFAULT_FINANCIAL_ASSET_2.doubleValue())))
            .andExpect(jsonPath("$.[*].financialAsset3").value(hasItem(DEFAULT_FINANCIAL_ASSET_3.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit1").value(hasItem(DEFAULT_OPERATING_PROFIT_1.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit2").value(hasItem(DEFAULT_OPERATING_PROFIT_2.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingProfit3").value(hasItem(DEFAULT_OPERATING_PROFIT_3.doubleValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].investmentObjective").value(hasItem(DEFAULT_INVESTMENT_OBJECTIVE)))
            .andExpect(jsonPath("$.[*].directSid").value(hasItem(DEFAULT_DIRECT_SID)))
            .andExpect(jsonPath("$.[*].assetOwner").value(hasItem(DEFAULT_ASSET_OWNER)))
            .andExpect(jsonPath("$.[*].supDocType").value(hasItem(DEFAULT_SUP_DOC_TYPE)))
            .andExpect(jsonPath("$.[*].supDocExpDate").value(hasItem(DEFAULT_SUP_DOC_EXP_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getInvestorInstitution() throws Exception {
        // Initialize the database
        investorInstitutionRepository.saveAndFlush(investorInstitution);

        // Get the investorInstitution
        restInvestorInstitutionMockMvc.perform(get("/api/investor-institutions/{id}", investorInstitution.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investorInstitution.getId().intValue()))
            .andExpect(jsonPath("$.sid").value(DEFAULT_SID))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME))
            .andExpect(jsonPath("$.bicCode").value(DEFAULT_BIC_CODE))
            .andExpect(jsonPath("$.legalDomicileId").value(DEFAULT_LEGAL_DOMICILE_ID.intValue()))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.skd").value(DEFAULT_SKD))
            .andExpect(jsonPath("$.skdExpDate").value(DEFAULT_SKD_EXP_DATE.toString()))
            .andExpect(jsonPath("$.companyEstablishPlce").value(DEFAULT_COMPANY_ESTABLISH_PLCE))
            .andExpect(jsonPath("$.companyEstablishDate").value(DEFAULT_COMPANY_ESTABLISH_DATE.toString()))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE))
            .andExpect(jsonPath("$.companyChracteristic").value(DEFAULT_COMPANY_CHRACTERISTIC))
            .andExpect(jsonPath("$.fundSource").value(DEFAULT_FUND_SOURCE))
            .andExpect(jsonPath("$.fundSourceText").value(DEFAULT_FUND_SOURCE_TEXT))
            .andExpect(jsonPath("$.articleAssociation").value(DEFAULT_ARTICLE_ASSOCIATION))
            .andExpect(jsonPath("$.bussinessRegNo").value(DEFAULT_BUSSINESS_REG_NO))
            .andExpect(jsonPath("$.financialAsset1").value(DEFAULT_FINANCIAL_ASSET_1.doubleValue()))
            .andExpect(jsonPath("$.financialAsset2").value(DEFAULT_FINANCIAL_ASSET_2.doubleValue()))
            .andExpect(jsonPath("$.financialAsset3").value(DEFAULT_FINANCIAL_ASSET_3.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit1").value(DEFAULT_OPERATING_PROFIT_1.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit2").value(DEFAULT_OPERATING_PROFIT_2.doubleValue()))
            .andExpect(jsonPath("$.operatingProfit3").value(DEFAULT_OPERATING_PROFIT_3.doubleValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.investmentObjective").value(DEFAULT_INVESTMENT_OBJECTIVE))
            .andExpect(jsonPath("$.directSid").value(DEFAULT_DIRECT_SID))
            .andExpect(jsonPath("$.assetOwner").value(DEFAULT_ASSET_OWNER))
            .andExpect(jsonPath("$.supDocType").value(DEFAULT_SUP_DOC_TYPE))
            .andExpect(jsonPath("$.supDocExpDate").value(DEFAULT_SUP_DOC_EXP_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInvestorInstitution() throws Exception {
        // Get the investorInstitution
        restInvestorInstitutionMockMvc.perform(get("/api/investor-institutions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestorInstitution() throws Exception {
        // Initialize the database
        investorInstitutionRepository.saveAndFlush(investorInstitution);

        int databaseSizeBeforeUpdate = investorInstitutionRepository.findAll().size();

        // Update the investorInstitution
        InvestorInstitution updatedInvestorInstitution = investorInstitutionRepository.findById(investorInstitution.getId()).get();
        // Disconnect from session so that the updates on updatedInvestorInstitution are not directly saved in db
        em.detach(updatedInvestorInstitution);
        updatedInvestorInstitution
            .sid(UPDATED_SID)
            .companyName(UPDATED_COMPANY_NAME)
            .bicCode(UPDATED_BIC_CODE)
            .legalDomicileId(UPDATED_LEGAL_DOMICILE_ID)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .skd(UPDATED_SKD)
            .skdExpDate(UPDATED_SKD_EXP_DATE)
            .companyEstablishPlce(UPDATED_COMPANY_ESTABLISH_PLCE)
            .companyEstablishDate(UPDATED_COMPANY_ESTABLISH_DATE)
            .businessType(UPDATED_BUSINESS_TYPE)
            .companyChracteristic(UPDATED_COMPANY_CHRACTERISTIC)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .articleAssociation(UPDATED_ARTICLE_ASSOCIATION)
            .bussinessRegNo(UPDATED_BUSSINESS_REG_NO)
            .financialAsset1(UPDATED_FINANCIAL_ASSET_1)
            .financialAsset2(UPDATED_FINANCIAL_ASSET_2)
            .financialAsset3(UPDATED_FINANCIAL_ASSET_3)
            .operatingProfit1(UPDATED_OPERATING_PROFIT_1)
            .operatingProfit2(UPDATED_OPERATING_PROFIT_2)
            .operatingProfit3(UPDATED_OPERATING_PROFIT_3)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .supDocType(UPDATED_SUP_DOC_TYPE)
            .supDocExpDate(UPDATED_SUP_DOC_EXP_DATE);
        InvestorInstitutionDTO investorInstitutionDTO = investorInstitutionMapper.toDto(updatedInvestorInstitution);

        restInvestorInstitutionMockMvc.perform(put("/api/investor-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorInstitutionDTO)))
            .andExpect(status().isOk());

        // Validate the InvestorInstitution in the database
        List<InvestorInstitution> investorInstitutionList = investorInstitutionRepository.findAll();
        assertThat(investorInstitutionList).hasSize(databaseSizeBeforeUpdate);
        InvestorInstitution testInvestorInstitution = investorInstitutionList.get(investorInstitutionList.size() - 1);
        assertThat(testInvestorInstitution.getSid()).isEqualTo(UPDATED_SID);
        assertThat(testInvestorInstitution.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testInvestorInstitution.getBicCode()).isEqualTo(UPDATED_BIC_CODE);
        assertThat(testInvestorInstitution.getLegalDomicileId()).isEqualTo(UPDATED_LEGAL_DOMICILE_ID);
        assertThat(testInvestorInstitution.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testInvestorInstitution.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testInvestorInstitution.getSkd()).isEqualTo(UPDATED_SKD);
        assertThat(testInvestorInstitution.getSkdExpDate()).isEqualTo(UPDATED_SKD_EXP_DATE);
        assertThat(testInvestorInstitution.getCompanyEstablishPlce()).isEqualTo(UPDATED_COMPANY_ESTABLISH_PLCE);
        assertThat(testInvestorInstitution.getCompanyEstablishDate()).isEqualTo(UPDATED_COMPANY_ESTABLISH_DATE);
        assertThat(testInvestorInstitution.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testInvestorInstitution.getCompanyChracteristic()).isEqualTo(UPDATED_COMPANY_CHRACTERISTIC);
        assertThat(testInvestorInstitution.getFundSource()).isEqualTo(UPDATED_FUND_SOURCE);
        assertThat(testInvestorInstitution.getFundSourceText()).isEqualTo(UPDATED_FUND_SOURCE_TEXT);
        assertThat(testInvestorInstitution.getArticleAssociation()).isEqualTo(UPDATED_ARTICLE_ASSOCIATION);
        assertThat(testInvestorInstitution.getBussinessRegNo()).isEqualTo(UPDATED_BUSSINESS_REG_NO);
        assertThat(testInvestorInstitution.getFinancialAsset1()).isEqualTo(UPDATED_FINANCIAL_ASSET_1);
        assertThat(testInvestorInstitution.getFinancialAsset2()).isEqualTo(UPDATED_FINANCIAL_ASSET_2);
        assertThat(testInvestorInstitution.getFinancialAsset3()).isEqualTo(UPDATED_FINANCIAL_ASSET_3);
        assertThat(testInvestorInstitution.getOperatingProfit1()).isEqualTo(UPDATED_OPERATING_PROFIT_1);
        assertThat(testInvestorInstitution.getOperatingProfit2()).isEqualTo(UPDATED_OPERATING_PROFIT_2);
        assertThat(testInvestorInstitution.getOperatingProfit3()).isEqualTo(UPDATED_OPERATING_PROFIT_3);
        assertThat(testInvestorInstitution.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInvestorInstitution.getInvestmentObjective()).isEqualTo(UPDATED_INVESTMENT_OBJECTIVE);
        assertThat(testInvestorInstitution.getDirectSid()).isEqualTo(UPDATED_DIRECT_SID);
        assertThat(testInvestorInstitution.getAssetOwner()).isEqualTo(UPDATED_ASSET_OWNER);
        assertThat(testInvestorInstitution.getSupDocType()).isEqualTo(UPDATED_SUP_DOC_TYPE);
        assertThat(testInvestorInstitution.getSupDocExpDate()).isEqualTo(UPDATED_SUP_DOC_EXP_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestorInstitution() throws Exception {
        int databaseSizeBeforeUpdate = investorInstitutionRepository.findAll().size();

        // Create the InvestorInstitution
        InvestorInstitutionDTO investorInstitutionDTO = investorInstitutionMapper.toDto(investorInstitution);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvestorInstitutionMockMvc.perform(put("/api/investor-institutions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorInstitutionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorInstitution in the database
        List<InvestorInstitution> investorInstitutionList = investorInstitutionRepository.findAll();
        assertThat(investorInstitutionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestorInstitution() throws Exception {
        // Initialize the database
        investorInstitutionRepository.saveAndFlush(investorInstitution);

        int databaseSizeBeforeDelete = investorInstitutionRepository.findAll().size();

        // Delete the investorInstitution
        restInvestorInstitutionMockMvc.perform(delete("/api/investor-institutions/{id}", investorInstitution.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvestorInstitution> investorInstitutionList = investorInstitutionRepository.findAll();
        assertThat(investorInstitutionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorInstitution.class);
        InvestorInstitution investorInstitution1 = new InvestorInstitution();
        investorInstitution1.setId(1L);
        InvestorInstitution investorInstitution2 = new InvestorInstitution();
        investorInstitution2.setId(investorInstitution1.getId());
        assertThat(investorInstitution1).isEqualTo(investorInstitution2);
        investorInstitution2.setId(2L);
        assertThat(investorInstitution1).isNotEqualTo(investorInstitution2);
        investorInstitution1.setId(null);
        assertThat(investorInstitution1).isNotEqualTo(investorInstitution2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorInstitutionDTO.class);
        InvestorInstitutionDTO investorInstitutionDTO1 = new InvestorInstitutionDTO();
        investorInstitutionDTO1.setId(1L);
        InvestorInstitutionDTO investorInstitutionDTO2 = new InvestorInstitutionDTO();
        assertThat(investorInstitutionDTO1).isNotEqualTo(investorInstitutionDTO2);
        investorInstitutionDTO2.setId(investorInstitutionDTO1.getId());
        assertThat(investorInstitutionDTO1).isEqualTo(investorInstitutionDTO2);
        investorInstitutionDTO2.setId(2L);
        assertThat(investorInstitutionDTO1).isNotEqualTo(investorInstitutionDTO2);
        investorInstitutionDTO1.setId(null);
        assertThat(investorInstitutionDTO1).isNotEqualTo(investorInstitutionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorInstitutionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorInstitutionMapper.fromId(null)).isNull();
    }
}
