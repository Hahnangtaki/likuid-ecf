package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.InvestorAuthorizePerson;
import id.tech.cakra.likuidecf.repository.InvestorAuthorizePersonRepository;
import id.tech.cakra.likuidecf.service.InvestorAuthorizePersonService;
import id.tech.cakra.likuidecf.service.dto.InvestorAuthorizePersonDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorAuthorizePersonMapper;
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
 * Integration tests for the {@link InvestorAuthorizePersonResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class InvestorAuthorizePersonResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

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

    @Autowired
    private InvestorAuthorizePersonRepository investorAuthorizePersonRepository;

    @Autowired
    private InvestorAuthorizePersonMapper investorAuthorizePersonMapper;

    @Autowired
    private InvestorAuthorizePersonService investorAuthorizePersonService;

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

    private MockMvc restInvestorAuthorizePersonMockMvc;

    private InvestorAuthorizePerson investorAuthorizePerson;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorAuthorizePersonResource investorAuthorizePersonResource = new InvestorAuthorizePersonResource(investorAuthorizePersonService);
        this.restInvestorAuthorizePersonMockMvc = MockMvcBuilders.standaloneSetup(investorAuthorizePersonResource)
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
    public static InvestorAuthorizePerson createEntity(EntityManager em) {
        InvestorAuthorizePerson investorAuthorizePerson = new InvestorAuthorizePerson()
            .firstName(DEFAULT_FIRST_NAME)
            .middleName(DEFAULT_MIDDLE_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .position(DEFAULT_POSITION)
            .ktp(DEFAULT_KTP)
            .ktpExpDate(DEFAULT_KTP_EXP_DATE)
            .npwp(DEFAULT_NPWP)
            .npwpRegDate(DEFAULT_NPWP_REG_DATE)
            .passport(DEFAULT_PASSPORT)
            .passportExpDate(DEFAULT_PASSPORT_EXP_DATE)
            .kitas(DEFAULT_KITAS)
            .kitasExpDate(DEFAULT_KITAS_EXP_DATE);
        return investorAuthorizePerson;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvestorAuthorizePerson createUpdatedEntity(EntityManager em) {
        InvestorAuthorizePerson investorAuthorizePerson = new InvestorAuthorizePerson()
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .position(UPDATED_POSITION)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE);
        return investorAuthorizePerson;
    }

    @BeforeEach
    public void initTest() {
        investorAuthorizePerson = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestorAuthorizePerson() throws Exception {
        int databaseSizeBeforeCreate = investorAuthorizePersonRepository.findAll().size();

        // Create the InvestorAuthorizePerson
        InvestorAuthorizePersonDTO investorAuthorizePersonDTO = investorAuthorizePersonMapper.toDto(investorAuthorizePerson);
        restInvestorAuthorizePersonMockMvc.perform(post("/api/investor-authorize-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAuthorizePersonDTO)))
            .andExpect(status().isCreated());

        // Validate the InvestorAuthorizePerson in the database
        List<InvestorAuthorizePerson> investorAuthorizePersonList = investorAuthorizePersonRepository.findAll();
        assertThat(investorAuthorizePersonList).hasSize(databaseSizeBeforeCreate + 1);
        InvestorAuthorizePerson testInvestorAuthorizePerson = investorAuthorizePersonList.get(investorAuthorizePersonList.size() - 1);
        assertThat(testInvestorAuthorizePerson.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testInvestorAuthorizePerson.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testInvestorAuthorizePerson.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testInvestorAuthorizePerson.getPosition()).isEqualTo(DEFAULT_POSITION);
        assertThat(testInvestorAuthorizePerson.getKtp()).isEqualTo(DEFAULT_KTP);
        assertThat(testInvestorAuthorizePerson.getKtpExpDate()).isEqualTo(DEFAULT_KTP_EXP_DATE);
        assertThat(testInvestorAuthorizePerson.getNpwp()).isEqualTo(DEFAULT_NPWP);
        assertThat(testInvestorAuthorizePerson.getNpwpRegDate()).isEqualTo(DEFAULT_NPWP_REG_DATE);
        assertThat(testInvestorAuthorizePerson.getPassport()).isEqualTo(DEFAULT_PASSPORT);
        assertThat(testInvestorAuthorizePerson.getPassportExpDate()).isEqualTo(DEFAULT_PASSPORT_EXP_DATE);
        assertThat(testInvestorAuthorizePerson.getKitas()).isEqualTo(DEFAULT_KITAS);
        assertThat(testInvestorAuthorizePerson.getKitasExpDate()).isEqualTo(DEFAULT_KITAS_EXP_DATE);
    }

    @Test
    @Transactional
    public void createInvestorAuthorizePersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorAuthorizePersonRepository.findAll().size();

        // Create the InvestorAuthorizePerson with an existing ID
        investorAuthorizePerson.setId(1L);
        InvestorAuthorizePersonDTO investorAuthorizePersonDTO = investorAuthorizePersonMapper.toDto(investorAuthorizePerson);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorAuthorizePersonMockMvc.perform(post("/api/investor-authorize-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAuthorizePersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorAuthorizePerson in the database
        List<InvestorAuthorizePerson> investorAuthorizePersonList = investorAuthorizePersonRepository.findAll();
        assertThat(investorAuthorizePersonList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvestorAuthorizePeople() throws Exception {
        // Initialize the database
        investorAuthorizePersonRepository.saveAndFlush(investorAuthorizePerson);

        // Get all the investorAuthorizePersonList
        restInvestorAuthorizePersonMockMvc.perform(get("/api/investor-authorize-people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investorAuthorizePerson.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].ktp").value(hasItem(DEFAULT_KTP)))
            .andExpect(jsonPath("$.[*].ktpExpDate").value(hasItem(DEFAULT_KTP_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].npwp").value(hasItem(DEFAULT_NPWP)))
            .andExpect(jsonPath("$.[*].npwpRegDate").value(hasItem(DEFAULT_NPWP_REG_DATE.toString())))
            .andExpect(jsonPath("$.[*].passport").value(hasItem(DEFAULT_PASSPORT)))
            .andExpect(jsonPath("$.[*].passportExpDate").value(hasItem(DEFAULT_PASSPORT_EXP_DATE.toString())))
            .andExpect(jsonPath("$.[*].kitas").value(hasItem(DEFAULT_KITAS)))
            .andExpect(jsonPath("$.[*].kitasExpDate").value(hasItem(DEFAULT_KITAS_EXP_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getInvestorAuthorizePerson() throws Exception {
        // Initialize the database
        investorAuthorizePersonRepository.saveAndFlush(investorAuthorizePerson);

        // Get the investorAuthorizePerson
        restInvestorAuthorizePersonMockMvc.perform(get("/api/investor-authorize-people/{id}", investorAuthorizePerson.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investorAuthorizePerson.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.ktp").value(DEFAULT_KTP))
            .andExpect(jsonPath("$.ktpExpDate").value(DEFAULT_KTP_EXP_DATE.toString()))
            .andExpect(jsonPath("$.npwp").value(DEFAULT_NPWP))
            .andExpect(jsonPath("$.npwpRegDate").value(DEFAULT_NPWP_REG_DATE.toString()))
            .andExpect(jsonPath("$.passport").value(DEFAULT_PASSPORT))
            .andExpect(jsonPath("$.passportExpDate").value(DEFAULT_PASSPORT_EXP_DATE.toString()))
            .andExpect(jsonPath("$.kitas").value(DEFAULT_KITAS))
            .andExpect(jsonPath("$.kitasExpDate").value(DEFAULT_KITAS_EXP_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInvestorAuthorizePerson() throws Exception {
        // Get the investorAuthorizePerson
        restInvestorAuthorizePersonMockMvc.perform(get("/api/investor-authorize-people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestorAuthorizePerson() throws Exception {
        // Initialize the database
        investorAuthorizePersonRepository.saveAndFlush(investorAuthorizePerson);

        int databaseSizeBeforeUpdate = investorAuthorizePersonRepository.findAll().size();

        // Update the investorAuthorizePerson
        InvestorAuthorizePerson updatedInvestorAuthorizePerson = investorAuthorizePersonRepository.findById(investorAuthorizePerson.getId()).get();
        // Disconnect from session so that the updates on updatedInvestorAuthorizePerson are not directly saved in db
        em.detach(updatedInvestorAuthorizePerson);
        updatedInvestorAuthorizePerson
            .firstName(UPDATED_FIRST_NAME)
            .middleName(UPDATED_MIDDLE_NAME)
            .lastName(UPDATED_LAST_NAME)
            .position(UPDATED_POSITION)
            .ktp(UPDATED_KTP)
            .ktpExpDate(UPDATED_KTP_EXP_DATE)
            .npwp(UPDATED_NPWP)
            .npwpRegDate(UPDATED_NPWP_REG_DATE)
            .passport(UPDATED_PASSPORT)
            .passportExpDate(UPDATED_PASSPORT_EXP_DATE)
            .kitas(UPDATED_KITAS)
            .kitasExpDate(UPDATED_KITAS_EXP_DATE);
        InvestorAuthorizePersonDTO investorAuthorizePersonDTO = investorAuthorizePersonMapper.toDto(updatedInvestorAuthorizePerson);

        restInvestorAuthorizePersonMockMvc.perform(put("/api/investor-authorize-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAuthorizePersonDTO)))
            .andExpect(status().isOk());

        // Validate the InvestorAuthorizePerson in the database
        List<InvestorAuthorizePerson> investorAuthorizePersonList = investorAuthorizePersonRepository.findAll();
        assertThat(investorAuthorizePersonList).hasSize(databaseSizeBeforeUpdate);
        InvestorAuthorizePerson testInvestorAuthorizePerson = investorAuthorizePersonList.get(investorAuthorizePersonList.size() - 1);
        assertThat(testInvestorAuthorizePerson.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testInvestorAuthorizePerson.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testInvestorAuthorizePerson.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testInvestorAuthorizePerson.getPosition()).isEqualTo(UPDATED_POSITION);
        assertThat(testInvestorAuthorizePerson.getKtp()).isEqualTo(UPDATED_KTP);
        assertThat(testInvestorAuthorizePerson.getKtpExpDate()).isEqualTo(UPDATED_KTP_EXP_DATE);
        assertThat(testInvestorAuthorizePerson.getNpwp()).isEqualTo(UPDATED_NPWP);
        assertThat(testInvestorAuthorizePerson.getNpwpRegDate()).isEqualTo(UPDATED_NPWP_REG_DATE);
        assertThat(testInvestorAuthorizePerson.getPassport()).isEqualTo(UPDATED_PASSPORT);
        assertThat(testInvestorAuthorizePerson.getPassportExpDate()).isEqualTo(UPDATED_PASSPORT_EXP_DATE);
        assertThat(testInvestorAuthorizePerson.getKitas()).isEqualTo(UPDATED_KITAS);
        assertThat(testInvestorAuthorizePerson.getKitasExpDate()).isEqualTo(UPDATED_KITAS_EXP_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestorAuthorizePerson() throws Exception {
        int databaseSizeBeforeUpdate = investorAuthorizePersonRepository.findAll().size();

        // Create the InvestorAuthorizePerson
        InvestorAuthorizePersonDTO investorAuthorizePersonDTO = investorAuthorizePersonMapper.toDto(investorAuthorizePerson);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvestorAuthorizePersonMockMvc.perform(put("/api/investor-authorize-people")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAuthorizePersonDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorAuthorizePerson in the database
        List<InvestorAuthorizePerson> investorAuthorizePersonList = investorAuthorizePersonRepository.findAll();
        assertThat(investorAuthorizePersonList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestorAuthorizePerson() throws Exception {
        // Initialize the database
        investorAuthorizePersonRepository.saveAndFlush(investorAuthorizePerson);

        int databaseSizeBeforeDelete = investorAuthorizePersonRepository.findAll().size();

        // Delete the investorAuthorizePerson
        restInvestorAuthorizePersonMockMvc.perform(delete("/api/investor-authorize-people/{id}", investorAuthorizePerson.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvestorAuthorizePerson> investorAuthorizePersonList = investorAuthorizePersonRepository.findAll();
        assertThat(investorAuthorizePersonList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAuthorizePerson.class);
        InvestorAuthorizePerson investorAuthorizePerson1 = new InvestorAuthorizePerson();
        investorAuthorizePerson1.setId(1L);
        InvestorAuthorizePerson investorAuthorizePerson2 = new InvestorAuthorizePerson();
        investorAuthorizePerson2.setId(investorAuthorizePerson1.getId());
        assertThat(investorAuthorizePerson1).isEqualTo(investorAuthorizePerson2);
        investorAuthorizePerson2.setId(2L);
        assertThat(investorAuthorizePerson1).isNotEqualTo(investorAuthorizePerson2);
        investorAuthorizePerson1.setId(null);
        assertThat(investorAuthorizePerson1).isNotEqualTo(investorAuthorizePerson2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAuthorizePersonDTO.class);
        InvestorAuthorizePersonDTO investorAuthorizePersonDTO1 = new InvestorAuthorizePersonDTO();
        investorAuthorizePersonDTO1.setId(1L);
        InvestorAuthorizePersonDTO investorAuthorizePersonDTO2 = new InvestorAuthorizePersonDTO();
        assertThat(investorAuthorizePersonDTO1).isNotEqualTo(investorAuthorizePersonDTO2);
        investorAuthorizePersonDTO2.setId(investorAuthorizePersonDTO1.getId());
        assertThat(investorAuthorizePersonDTO1).isEqualTo(investorAuthorizePersonDTO2);
        investorAuthorizePersonDTO2.setId(2L);
        assertThat(investorAuthorizePersonDTO1).isNotEqualTo(investorAuthorizePersonDTO2);
        investorAuthorizePersonDTO1.setId(null);
        assertThat(investorAuthorizePersonDTO1).isNotEqualTo(investorAuthorizePersonDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorAuthorizePersonMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorAuthorizePersonMapper.fromId(null)).isNull();
    }
}
