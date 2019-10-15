package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.InvestorIndividu;
import id.tech.cakra.likuidecf.repository.InvestorIndividuRepository;
import id.tech.cakra.likuidecf.service.InvestorIndividuService;
import id.tech.cakra.likuidecf.service.dto.InvestorIndividuDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorIndividuMapper;
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
 * Integration tests for the {@link InvestorIndividuResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class InvestorIndividuResourceIT {

    private static final String DEFAULT_SID = "AAAAAAAAAA";
    private static final String UPDATED_SID = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_NATIONALITY_ID = 1L;
    private static final Long UPDATED_NATIONALITY_ID = 2L;

    private static final String DEFAULT_KTP = "AAAAAAAAAA";
    private static final String UPDATED_KTP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_KTP_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_KTP_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NPWP = "AAAAAAAAAA";
    private static final String UPDATED_NPWP = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NPWP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NPWP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PASSPORT = "AAAAAAAAAA";
    private static final String UPDATED_PASSPORT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PASSPORT_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PASSPORT_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_KITAS = "AAAAAAAAAA";
    private static final String UPDATED_KITAS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_KITAS_EXP_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_KITAS_EXP_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BIRTH_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_PLACE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SEX = "A";
    private static final String UPDATED_SEX = "B";

    private static final String DEFAULT_MARITAL_STATUS = "A";
    private static final String UPDATED_MARITAL_STATUS = "B";

    private static final String DEFAULT_SPOUSE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SPOUSE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HEIR = "AAAAAAAAAA";
    private static final String UPDATED_HEIR = "BBBBBBBBBB";

    private static final String DEFAULT_HEIR_RALATION = "AAAAAAAAAA";
    private static final String UPDATED_HEIR_RALATION = "BBBBBBBBBB";

    private static final String DEFAULT_EDUCATION_BACKGROUND = "A";
    private static final String UPDATED_EDUCATION_BACKGROUND = "B";

    private static final String DEFAULT_OCCUPATION = "A";
    private static final String UPDATED_OCCUPATION = "B";

    private static final String DEFAULT_NATURE_OF_BUSINESS = "AAAAAAAAAA";
    private static final String UPDATED_NATURE_OF_BUSINESS = "BBBBBBBBBB";

    private static final Double DEFAULT_ANNUAL_INCOME = 1D;
    private static final Double UPDATED_ANNUAL_INCOME = 2D;

    private static final String DEFAULT_FUND_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_FUND_SOURCE_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_FUND_SOURCE_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_INVESTMENT_OBJECTIVE = "AAAAAAAAAA";
    private static final String UPDATED_INVESTMENT_OBJECTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_MOTHER_MAIDEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MOTHER_MAIDEN_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECT_SID = "AAAAAAAAAA";
    private static final String UPDATED_DIRECT_SID = "BBBBBBBBBB";

    private static final String DEFAULT_ASSET_OWNER = "A";
    private static final String UPDATED_ASSET_OWNER = "B";

    private static final LocalDate DEFAULT_AUTH_PERSON_KTP_REG_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_AUTH_PERSON_KTP_REG_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private InvestorIndividuRepository investorIndividuRepository;

    @Autowired
    private InvestorIndividuMapper investorIndividuMapper;

    @Autowired
    private InvestorIndividuService investorIndividuService;

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

    private MockMvc restInvestorIndividuMockMvc;

    private InvestorIndividu investorIndividu;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorIndividuResource investorIndividuResource = new InvestorIndividuResource(investorIndividuService);
        this.restInvestorIndividuMockMvc = MockMvcBuilders.standaloneSetup(investorIndividuResource)
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
    public static InvestorIndividu createEntity(EntityManager em) {
        InvestorIndividu investorIndividu = new InvestorIndividu()
            .sid(DEFAULT_SID)
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .nationalityId(DEFAULT_NATIONALITY_ID)
            .ktp(DEFAULT_KTP)
            .ktpExpDate(DEFAULT_KTP_EXP_DATE)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .passport(DEFAULT_PASSPORT)
            .passportExpDate(DEFAULT_PASSPORT_EXP_DATE)
            .kitas(DEFAULT_KITAS)
            .kitasExpDate(DEFAULT_KITAS_EXP_DATE)
            .birthPlace(DEFAULT_BIRTH_PLACE)
            .birthDate(DEFAULT_BIRTH_DATE)
            .sex(DEFAULT_SEX)
            .maritalStatus(DEFAULT_MARITAL_STATUS)
            .spouseName(DEFAULT_SPOUSE_NAME)
            .heir(DEFAULT_HEIR)
            .heirRalation(DEFAULT_HEIR_RALATION)
            .educationBackground(DEFAULT_EDUCATION_BACKGROUND)
            .occupation(DEFAULT_OCCUPATION)
            .natureOfBusiness(DEFAULT_NATURE_OF_BUSINESS)
            .annualIncome(DEFAULT_ANNUAL_INCOME)
            .fundSource(DEFAULT_FUND_SOURCE)
            .fundSourceText(DEFAULT_FUND_SOURCE_TEXT)
            .description(DEFAULT_DESCRIPTION)
            .investmentObjective(DEFAULT_INVESTMENT_OBJECTIVE)
            .motherMaidenName(DEFAULT_MOTHER_MAIDEN_NAME)
            .directSid(DEFAULT_DIRECT_SID)
            .assetOwner(DEFAULT_ASSET_OWNER)
            .authPersonKtpRegDate(DEFAULT_AUTH_PERSON_KTP_REG_DATE);
        return investorIndividu;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvestorIndividu createUpdatedEntity(EntityManager em) {
        InvestorIndividu investorIndividu = new InvestorIndividu()
            .sid(UPDATED_SID)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .spouseName(UPDATED_SPOUSE_NAME)
            .heir(UPDATED_HEIR)
            .heirRalation(UPDATED_HEIR_RALATION)
            .educationBackground(UPDATED_EDUCATION_BACKGROUND)
            .occupation(UPDATED_OCCUPATION)
            .natureOfBusiness(UPDATED_NATURE_OF_BUSINESS)
            .annualIncome(UPDATED_ANNUAL_INCOME)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .motherMaidenName(UPDATED_MOTHER_MAIDEN_NAME)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .authPersonKtpRegDate(UPDATED_AUTH_PERSON_KTP_REG_DATE);
        return investorIndividu;
    }

    @BeforeEach
    public void initTest() {
        investorIndividu = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestorIndividu() throws Exception {
        int databaseSizeBeforeCreate = investorIndividuRepository.findAll().size();

        // Create the InvestorIndividu
        InvestorIndividuDTO investorIndividuDTO = investorIndividuMapper.toDto(investorIndividu);
        restInvestorIndividuMockMvc.perform(post("/api/investor-individus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorIndividuDTO)))
            .andExpect(status().isCreated());

        // Validate the InvestorIndividu in the database
        List<InvestorIndividu> investorIndividuList = investorIndividuRepository.findAll();
        assertThat(investorIndividuList).hasSize(databaseSizeBeforeCreate + 1);
        InvestorIndividu testInvestorIndividu = investorIndividuList.get(investorIndividuList.size() - 1);
        assertThat(testInvestorIndividu.getSid()).isEqualTo(DEFAULT_SID);
        assertThat(testInvestorIndividu.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testInvestorIndividu.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testInvestorIndividu.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testInvestorIndividu.getNationalityId()).isEqualTo(DEFAULT_NATIONALITY_ID);
        assertThat(testInvestorIndividu.getKtp()).isEqualTo(DEFAULT_KTP);
        assertThat(testInvestorIndividu.getKtpExpDate()).isEqualTo(DEFAULT_KTP_EXP_DATE);
        assertThat(testInvestorIndividu.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testInvestorIndividu.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testInvestorIndividu.getPassport()).isEqualTo(DEFAULT_PASSPORT);
        assertThat(testInvestorIndividu.getPassportExpDate()).isEqualTo(DEFAULT_PASSPORT_EXP_DATE);
        assertThat(testInvestorIndividu.getKitas()).isEqualTo(DEFAULT_KITAS);
        assertThat(testInvestorIndividu.getKitasExpDate()).isEqualTo(DEFAULT_KITAS_EXP_DATE);
        assertThat(testInvestorIndividu.getBirthPlace()).isEqualTo(DEFAULT_BIRTH_PLACE);
        assertThat(testInvestorIndividu.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testInvestorIndividu.getSex()).isEqualTo(DEFAULT_SEX);
        assertThat(testInvestorIndividu.getMaritalStatus()).isEqualTo(DEFAULT_MARITAL_STATUS);
        assertThat(testInvestorIndividu.getSpouseName()).isEqualTo(DEFAULT_SPOUSE_NAME);
        assertThat(testInvestorIndividu.getHeir()).isEqualTo(DEFAULT_HEIR);
        assertThat(testInvestorIndividu.getHeirRalation()).isEqualTo(DEFAULT_HEIR_RALATION);
        assertThat(testInvestorIndividu.getEducationBackground()).isEqualTo(DEFAULT_EDUCATION_BACKGROUND);
        assertThat(testInvestorIndividu.getOccupation()).isEqualTo(DEFAULT_OCCUPATION);
        assertThat(testInvestorIndividu.getNatureOfBusiness()).isEqualTo(DEFAULT_NATURE_OF_BUSINESS);
        assertThat(testInvestorIndividu.getAnnualIncome()).isEqualTo(DEFAULT_ANNUAL_INCOME);
        assertThat(testInvestorIndividu.getFundSource()).isEqualTo(DEFAULT_FUND_SOURCE);
        assertThat(testInvestorIndividu.getFundSourceText()).isEqualTo(DEFAULT_FUND_SOURCE_TEXT);
        assertThat(testInvestorIndividu.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testInvestorIndividu.getInvestmentObjective()).isEqualTo(DEFAULT_INVESTMENT_OBJECTIVE);
        assertThat(testInvestorIndividu.getMotherMaidenName()).isEqualTo(DEFAULT_MOTHER_MAIDEN_NAME);
        assertThat(testInvestorIndividu.getDirectSid()).isEqualTo(DEFAULT_DIRECT_SID);
        assertThat(testInvestorIndividu.getAssetOwner()).isEqualTo(DEFAULT_ASSET_OWNER);
        assertThat(testInvestorIndividu.getAuthPersonKtpRegDate()).isEqualTo(DEFAULT_AUTH_PERSON_KTP_REG_DATE);
    }

    @Test
    @Transactional
    public void createInvestorIndividuWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorIndividuRepository.findAll().size();

        // Create the InvestorIndividu with an existing ID
        investorIndividu.setId(1L);
        InvestorIndividuDTO investorIndividuDTO = investorIndividuMapper.toDto(investorIndividu);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorIndividuMockMvc.perform(post("/api/investor-individus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorIndividuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorIndividu in the database
        List<InvestorIndividu> investorIndividuList = investorIndividuRepository.findAll();
        assertThat(investorIndividuList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvestorIndividus() throws Exception {
        // Initialize the database
        investorIndividuRepository.saveAndFlush(investorIndividu);

        // Get all the investorIndividuList
        restInvestorIndividuMockMvc.perform(get("/api/investor-individus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investorIndividu.getId().intValue())))
            .andExpect(jsonPath("$.[*].sid").value(hasItem(DEFAULT_SID)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].nationalityId").value(hasItem(DEFAULT_NATIONALITY_ID.intValue())))
            .andExpect(jsonPath("$.[*].ktp").value(hasItem(DEFAULT_KTP)))
            .andExpect(jsonPath("$.[*].ktpExpDate").value(hasItem(DEFAULT_KTP_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].passport").value(hasItem(DEFAULT_PASSPORT)))
            .andExpect(jsonPath("$.[*].passportExpDate").value(hasItem(DEFAULT_PASSPORT_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].kitas").value(hasItem(DEFAULT_KITAS)))
            .andExpect(jsonPath("$.[*].kitasExpDate").value(hasItem(DEFAULT_KITAS_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].birthPlace").value(hasItem(DEFAULT_BIRTH_PLACE)))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(DEFAULT_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].sex").value(hasItem(DEFAULT_SEX)))
            .andExpect(jsonPath("$.[*].maritalStatus").value(hasItem(DEFAULT_MARITAL_STATUS)))
            .andExpect(jsonPath("$.[*].spouseName").value(hasItem(DEFAULT_SPOUSE_NAME)))
            .andExpect(jsonPath("$.[*].heir").value(hasItem(DEFAULT_HEIR)))
            .andExpect(jsonPath("$.[*].heirRalation").value(hasItem(DEFAULT_HEIR_RALATION)))
            .andExpect(jsonPath("$.[*].educationBackground").value(hasItem(DEFAULT_EDUCATION_BACKGROUND)))
            .andExpect(jsonPath("$.[*].occupation").value(hasItem(DEFAULT_OCCUPATION)))
            .andExpect(jsonPath("$.[*].natureOfBusiness").value(hasItem(DEFAULT_NATURE_OF_BUSINESS)))
            .andExpect(jsonPath("$.[*].annualIncome").value(hasItem(DEFAULT_ANNUAL_INCOME.doubleValue())))
            .andExpect(jsonPath("$.[*].fundSource").value(hasItem(DEFAULT_FUND_SOURCE)))
            .andExpect(jsonPath("$.[*].fundSourceText").value(hasItem(DEFAULT_FUND_SOURCE_TEXT)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].investmentObjective").value(hasItem(DEFAULT_INVESTMENT_OBJECTIVE)))
            .andExpect(jsonPath("$.[*].motherMaidenName").value(hasItem(DEFAULT_MOTHER_MAIDEN_NAME)))
            .andExpect(jsonPath("$.[*].directSid").value(hasItem(DEFAULT_DIRECT_SID)))
            .andExpect(jsonPath("$.[*].assetOwner").value(hasItem(DEFAULT_ASSET_OWNER)))
            .andExpect(jsonPath("$.[*].authPersonKtpRegDate").value(hasItem(DEFAULT_AUTH_PERSON_KTP_REG_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getInvestorIndividu() throws Exception {
        // Initialize the database
        investorIndividuRepository.saveAndFlush(investorIndividu);

        // Get the investorIndividu
        restInvestorIndividuMockMvc.perform(get("/api/investor-individus/{id}", investorIndividu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investorIndividu.getId().intValue()))
            .andExpect(jsonPath("$.sid").value(DEFAULT_SID))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.nationalityId").value(DEFAULT_NATIONALITY_ID.intValue()))
            .andExpect(jsonPath("$.ktp").value(DEFAULT_KTP))
            .andExpect(jsonPath("$.ktpExpDate").value(DEFAULT_KTP_EXP_DATE.toString()))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.passport").value(DEFAULT_PASSPORT))
            .andExpect(jsonPath("$.passportExpDate").value(DEFAULT_PASSPORT_EXP_DATE.toString()))
            .andExpect(jsonPath("$.kitas").value(DEFAULT_KITAS))
            .andExpect(jsonPath("$.kitasExpDate").value(DEFAULT_KITAS_EXP_DATE.toString()))
            .andExpect(jsonPath("$.birthPlace").value(DEFAULT_BIRTH_PLACE))
            .andExpect(jsonPath("$.birthDate").value(DEFAULT_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.sex").value(DEFAULT_SEX))
            .andExpect(jsonPath("$.maritalStatus").value(DEFAULT_MARITAL_STATUS))
            .andExpect(jsonPath("$.spouseName").value(DEFAULT_SPOUSE_NAME))
            .andExpect(jsonPath("$.heir").value(DEFAULT_HEIR))
            .andExpect(jsonPath("$.heirRalation").value(DEFAULT_HEIR_RALATION))
            .andExpect(jsonPath("$.educationBackground").value(DEFAULT_EDUCATION_BACKGROUND))
            .andExpect(jsonPath("$.occupation").value(DEFAULT_OCCUPATION))
            .andExpect(jsonPath("$.natureOfBusiness").value(DEFAULT_NATURE_OF_BUSINESS))
            .andExpect(jsonPath("$.annualIncome").value(DEFAULT_ANNUAL_INCOME.doubleValue()))
            .andExpect(jsonPath("$.fundSource").value(DEFAULT_FUND_SOURCE))
            .andExpect(jsonPath("$.fundSourceText").value(DEFAULT_FUND_SOURCE_TEXT))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.investmentObjective").value(DEFAULT_INVESTMENT_OBJECTIVE))
            .andExpect(jsonPath("$.motherMaidenName").value(DEFAULT_MOTHER_MAIDEN_NAME))
            .andExpect(jsonPath("$.directSid").value(DEFAULT_DIRECT_SID))
            .andExpect(jsonPath("$.assetOwner").value(DEFAULT_ASSET_OWNER))
            .andExpect(jsonPath("$.authPersonKtpRegDate").value(DEFAULT_AUTH_PERSON_KTP_REG_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInvestorIndividu() throws Exception {
        // Get the investorIndividu
        restInvestorIndividuMockMvc.perform(get("/api/investor-individus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestorIndividu() throws Exception {
        // Initialize the database
        investorIndividuRepository.saveAndFlush(investorIndividu);

        int databaseSizeBeforeUpdate = investorIndividuRepository.findAll().size();

        // Update the investorIndividu
        InvestorIndividu updatedInvestorIndividu = investorIndividuRepository.findById(investorIndividu.getId()).get();
        // Disconnect from session so that the updates on updatedInvestorIndividu are not directly saved in db
        em.detach(updatedInvestorIndividu);
        updatedInvestorIndividu
            .sid(UPDATED_SID)
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .nationalityId(UPDATED_NATIONALITY_ID)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE)
            .birthPlace(UPDATED_BIRTH_PLACE)
            .birthDate(UPDATED_BIRTH_DATE)
            .sex(UPDATED_SEX)
            .maritalStatus(UPDATED_MARITAL_STATUS)
            .spouseName(UPDATED_SPOUSE_NAME)
            .heir(UPDATED_HEIR)
            .heirRalation(UPDATED_HEIR_RALATION)
            .educationBackground(UPDATED_EDUCATION_BACKGROUND)
            .occupation(UPDATED_OCCUPATION)
            .natureOfBusiness(UPDATED_NATURE_OF_BUSINESS)
            .annualIncome(UPDATED_ANNUAL_INCOME)
            .fundSource(UPDATED_FUND_SOURCE)
            .fundSourceText(UPDATED_FUND_SOURCE_TEXT)
            .description(UPDATED_DESCRIPTION)
            .investmentObjective(UPDATED_INVESTMENT_OBJECTIVE)
            .motherMaidenName(UPDATED_MOTHER_MAIDEN_NAME)
            .directSid(UPDATED_DIRECT_SID)
            .assetOwner(UPDATED_ASSET_OWNER)
            .authPersonKtpRegDate(UPDATED_AUTH_PERSON_KTP_REG_DATE);
        InvestorIndividuDTO investorIndividuDTO = investorIndividuMapper.toDto(updatedInvestorIndividu);

        restInvestorIndividuMockMvc.perform(put("/api/investor-individus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorIndividuDTO)))
            .andExpect(status().isOk());

        // Validate the InvestorIndividu in the database
        List<InvestorIndividu> investorIndividuList = investorIndividuRepository.findAll();
        assertThat(investorIndividuList).hasSize(databaseSizeBeforeUpdate);
        InvestorIndividu testInvestorIndividu = investorIndividuList.get(investorIndividuList.size() - 1);
        assertThat(testInvestorIndividu.getSid()).isEqualTo(UPDATED_SID);
        assertThat(testInvestorIndividu.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testInvestorIndividu.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testInvestorIndividu.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testInvestorIndividu.getNationalityId()).isEqualTo(UPDATED_NATIONALITY_ID);
        assertThat(testInvestorIndividu.getKtp()).isEqualTo(UPDATED_KTP);
        assertThat(testInvestorIndividu.getKtpExpDate()).isEqualTo(UPDATED_KTP_EXP_DATE);
        assertThat(testInvestorIndividu.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testInvestorIndividu.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testInvestorIndividu.getPassport()).isEqualTo(UPDATED_PASSPORT);
        assertThat(testInvestorIndividu.getPassportExpDate()).isEqualTo(UPDATED_PASSPORT_EXP_DATE);
        assertThat(testInvestorIndividu.getKitas()).isEqualTo(UPDATED_KITAS);
        assertThat(testInvestorIndividu.getKitasExpDate()).isEqualTo(UPDATED_KITAS_EXP_DATE);
        assertThat(testInvestorIndividu.getBirthPlace()).isEqualTo(UPDATED_BIRTH_PLACE);
        assertThat(testInvestorIndividu.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testInvestorIndividu.getSex()).isEqualTo(UPDATED_SEX);
        assertThat(testInvestorIndividu.getMaritalStatus()).isEqualTo(UPDATED_MARITAL_STATUS);
        assertThat(testInvestorIndividu.getSpouseName()).isEqualTo(UPDATED_SPOUSE_NAME);
        assertThat(testInvestorIndividu.getHeir()).isEqualTo(UPDATED_HEIR);
        assertThat(testInvestorIndividu.getHeirRalation()).isEqualTo(UPDATED_HEIR_RALATION);
        assertThat(testInvestorIndividu.getEducationBackground()).isEqualTo(UPDATED_EDUCATION_BACKGROUND);
        assertThat(testInvestorIndividu.getOccupation()).isEqualTo(UPDATED_OCCUPATION);
        assertThat(testInvestorIndividu.getNatureOfBusiness()).isEqualTo(UPDATED_NATURE_OF_BUSINESS);
        assertThat(testInvestorIndividu.getAnnualIncome()).isEqualTo(UPDATED_ANNUAL_INCOME);
        assertThat(testInvestorIndividu.getFundSource()).isEqualTo(UPDATED_FUND_SOURCE);
        assertThat(testInvestorIndividu.getFundSourceText()).isEqualTo(UPDATED_FUND_SOURCE_TEXT);
        assertThat(testInvestorIndividu.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testInvestorIndividu.getInvestmentObjective()).isEqualTo(UPDATED_INVESTMENT_OBJECTIVE);
        assertThat(testInvestorIndividu.getMotherMaidenName()).isEqualTo(UPDATED_MOTHER_MAIDEN_NAME);
        assertThat(testInvestorIndividu.getDirectSid()).isEqualTo(UPDATED_DIRECT_SID);
        assertThat(testInvestorIndividu.getAssetOwner()).isEqualTo(UPDATED_ASSET_OWNER);
        assertThat(testInvestorIndividu.getAuthPersonKtpRegDate()).isEqualTo(UPDATED_AUTH_PERSON_KTP_REG_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestorIndividu() throws Exception {
        int databaseSizeBeforeUpdate = investorIndividuRepository.findAll().size();

        // Create the InvestorIndividu
        InvestorIndividuDTO investorIndividuDTO = investorIndividuMapper.toDto(investorIndividu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvestorIndividuMockMvc.perform(put("/api/investor-individus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorIndividuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorIndividu in the database
        List<InvestorIndividu> investorIndividuList = investorIndividuRepository.findAll();
        assertThat(investorIndividuList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestorIndividu() throws Exception {
        // Initialize the database
        investorIndividuRepository.saveAndFlush(investorIndividu);

        int databaseSizeBeforeDelete = investorIndividuRepository.findAll().size();

        // Delete the investorIndividu
        restInvestorIndividuMockMvc.perform(delete("/api/investor-individus/{id}", investorIndividu.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvestorIndividu> investorIndividuList = investorIndividuRepository.findAll();
        assertThat(investorIndividuList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorIndividu.class);
        InvestorIndividu investorIndividu1 = new InvestorIndividu();
        investorIndividu1.setId(1L);
        InvestorIndividu investorIndividu2 = new InvestorIndividu();
        investorIndividu2.setId(investorIndividu1.getId());
        assertThat(investorIndividu1).isEqualTo(investorIndividu2);
        investorIndividu2.setId(2L);
        assertThat(investorIndividu1).isNotEqualTo(investorIndividu2);
        investorIndividu1.setId(null);
        assertThat(investorIndividu1).isNotEqualTo(investorIndividu2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorIndividuDTO.class);
        InvestorIndividuDTO investorIndividuDTO1 = new InvestorIndividuDTO();
        investorIndividuDTO1.setId(1L);
        InvestorIndividuDTO investorIndividuDTO2 = new InvestorIndividuDTO();
        assertThat(investorIndividuDTO1).isNotEqualTo(investorIndividuDTO2);
        investorIndividuDTO2.setId(investorIndividuDTO1.getId());
        assertThat(investorIndividuDTO1).isEqualTo(investorIndividuDTO2);
        investorIndividuDTO2.setId(2L);
        assertThat(investorIndividuDTO1).isNotEqualTo(investorIndividuDTO2);
        investorIndividuDTO1.setId(null);
        assertThat(investorIndividuDTO1).isNotEqualTo(investorIndividuDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorIndividuMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorIndividuMapper.fromId(null)).isNull();
    }
}
