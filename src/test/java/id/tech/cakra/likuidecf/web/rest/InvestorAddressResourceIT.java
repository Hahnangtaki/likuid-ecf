package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.InvestorAddress;
import id.tech.cakra.likuidecf.repository.InvestorAddressRepository;
import id.tech.cakra.likuidecf.service.InvestorAddressService;
import id.tech.cakra.likuidecf.service.dto.InvestorAddressDTO;
import id.tech.cakra.likuidecf.service.mapper.InvestorAddressMapper;
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
 * Integration tests for the {@link InvestorAddressResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class InvestorAddressResourceIT {

    private static final String DEFAULT_ADDRESS_TYPE = "A";
    private static final String UPDATED_ADDRESS_TYPE = "B";

    private static final String DEFAULT_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_POSTAL_CODE = "AAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    @Autowired
    private InvestorAddressRepository investorAddressRepository;

    @Autowired
    private InvestorAddressMapper investorAddressMapper;

    @Autowired
    private InvestorAddressService investorAddressService;

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

    private MockMvc restInvestorAddressMockMvc;

    private InvestorAddress investorAddress;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestorAddressResource investorAddressResource = new InvestorAddressResource(investorAddressService);
        this.restInvestorAddressMockMvc = MockMvcBuilders.standaloneSetup(investorAddressResource)
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
    public static InvestorAddress createEntity(EntityManager em) {
        InvestorAddress investorAddress = new InvestorAddress()
            .addressType(DEFAULT_ADDRESS_TYPE)
            .address1(DEFAULT_ADDRESS_1)
            .address2(DEFAULT_ADDRESS_2)
            .address3(DEFAULT_ADDRESS_3)
            .postalCode(DEFAULT_POSTAL_CODE)
            .phone(DEFAULT_PHONE)
            .mobilePhone(DEFAULT_MOBILE_PHONE)
            .email(DEFAULT_EMAIL)
            .fax(DEFAULT_FAX);
        return investorAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InvestorAddress createUpdatedEntity(EntityManager em) {
        InvestorAddress investorAddress = new InvestorAddress()
            .addressType(UPDATED_ADDRESS_TYPE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .postalCode(UPDATED_POSTAL_CODE)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX);
        return investorAddress;
    }

    @BeforeEach
    public void initTest() {
        investorAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestorAddress() throws Exception {
        int databaseSizeBeforeCreate = investorAddressRepository.findAll().size();

        // Create the InvestorAddress
        InvestorAddressDTO investorAddressDTO = investorAddressMapper.toDto(investorAddress);
        restInvestorAddressMockMvc.perform(post("/api/investor-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressDTO)))
            .andExpect(status().isCreated());

        // Validate the InvestorAddress in the database
        List<InvestorAddress> investorAddressList = investorAddressRepository.findAll();
        assertThat(investorAddressList).hasSize(databaseSizeBeforeCreate + 1);
        InvestorAddress testInvestorAddress = investorAddressList.get(investorAddressList.size() - 1);
        assertThat(testInvestorAddress.getAddressType()).isEqualTo(DEFAULT_ADDRESS_TYPE);
        assertThat(testInvestorAddress.getAddress1()).isEqualTo(DEFAULT_ADDRESS_1);
        assertThat(testInvestorAddress.getAddress2()).isEqualTo(DEFAULT_ADDRESS_2);
        assertThat(testInvestorAddress.getAddress3()).isEqualTo(DEFAULT_ADDRESS_3);
        assertThat(testInvestorAddress.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testInvestorAddress.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testInvestorAddress.getMobilePhone()).isEqualTo(DEFAULT_MOBILE_PHONE);
        assertThat(testInvestorAddress.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testInvestorAddress.getFax()).isEqualTo(DEFAULT_FAX);
    }

    @Test
    @Transactional
    public void createInvestorAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investorAddressRepository.findAll().size();

        // Create the InvestorAddress with an existing ID
        investorAddress.setId(1L);
        InvestorAddressDTO investorAddressDTO = investorAddressMapper.toDto(investorAddress);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestorAddressMockMvc.perform(post("/api/investor-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorAddress in the database
        List<InvestorAddress> investorAddressList = investorAddressRepository.findAll();
        assertThat(investorAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInvestorAddresses() throws Exception {
        // Initialize the database
        investorAddressRepository.saveAndFlush(investorAddress);

        // Get all the investorAddressList
        restInvestorAddressMockMvc.perform(get("/api/investor-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investorAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressType").value(hasItem(DEFAULT_ADDRESS_TYPE)))
            .andExpect(jsonPath("$.[*].address1").value(hasItem(DEFAULT_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].address2").value(hasItem(DEFAULT_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].address3").value(hasItem(DEFAULT_ADDRESS_3)))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].mobilePhone").value(hasItem(DEFAULT_MOBILE_PHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)));
    }
    
    @Test
    @Transactional
    public void getInvestorAddress() throws Exception {
        // Initialize the database
        investorAddressRepository.saveAndFlush(investorAddress);

        // Get the investorAddress
        restInvestorAddressMockMvc.perform(get("/api/investor-addresses/{id}", investorAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investorAddress.getId().intValue()))
            .andExpect(jsonPath("$.addressType").value(DEFAULT_ADDRESS_TYPE))
            .andExpect(jsonPath("$.address1").value(DEFAULT_ADDRESS_1))
            .andExpect(jsonPath("$.address2").value(DEFAULT_ADDRESS_2))
            .andExpect(jsonPath("$.address3").value(DEFAULT_ADDRESS_3))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.mobilePhone").value(DEFAULT_MOBILE_PHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX));
    }

    @Test
    @Transactional
    public void getNonExistingInvestorAddress() throws Exception {
        // Get the investorAddress
        restInvestorAddressMockMvc.perform(get("/api/investor-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestorAddress() throws Exception {
        // Initialize the database
        investorAddressRepository.saveAndFlush(investorAddress);

        int databaseSizeBeforeUpdate = investorAddressRepository.findAll().size();

        // Update the investorAddress
        InvestorAddress updatedInvestorAddress = investorAddressRepository.findById(investorAddress.getId()).get();
        // Disconnect from session so that the updates on updatedInvestorAddress are not directly saved in db
        em.detach(updatedInvestorAddress);
        updatedInvestorAddress
            .addressType(UPDATED_ADDRESS_TYPE)
            .address1(UPDATED_ADDRESS_1)
            .address2(UPDATED_ADDRESS_2)
            .address3(UPDATED_ADDRESS_3)
            .postalCode(UPDATED_POSTAL_CODE)
            .phone(UPDATED_PHONE)
            .mobilePhone(UPDATED_MOBILE_PHONE)
            .email(UPDATED_EMAIL)
            .fax(UPDATED_FAX);
        InvestorAddressDTO investorAddressDTO = investorAddressMapper.toDto(updatedInvestorAddress);

        restInvestorAddressMockMvc.perform(put("/api/investor-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressDTO)))
            .andExpect(status().isOk());

        // Validate the InvestorAddress in the database
        List<InvestorAddress> investorAddressList = investorAddressRepository.findAll();
        assertThat(investorAddressList).hasSize(databaseSizeBeforeUpdate);
        InvestorAddress testInvestorAddress = investorAddressList.get(investorAddressList.size() - 1);
        assertThat(testInvestorAddress.getAddressType()).isEqualTo(UPDATED_ADDRESS_TYPE);
        assertThat(testInvestorAddress.getAddress1()).isEqualTo(UPDATED_ADDRESS_1);
        assertThat(testInvestorAddress.getAddress2()).isEqualTo(UPDATED_ADDRESS_2);
        assertThat(testInvestorAddress.getAddress3()).isEqualTo(UPDATED_ADDRESS_3);
        assertThat(testInvestorAddress.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testInvestorAddress.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testInvestorAddress.getMobilePhone()).isEqualTo(UPDATED_MOBILE_PHONE);
        assertThat(testInvestorAddress.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testInvestorAddress.getFax()).isEqualTo(UPDATED_FAX);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestorAddress() throws Exception {
        int databaseSizeBeforeUpdate = investorAddressRepository.findAll().size();

        // Create the InvestorAddress
        InvestorAddressDTO investorAddressDTO = investorAddressMapper.toDto(investorAddress);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInvestorAddressMockMvc.perform(put("/api/investor-addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investorAddressDTO)))
            .andExpect(status().isBadRequest());

        // Validate the InvestorAddress in the database
        List<InvestorAddress> investorAddressList = investorAddressRepository.findAll();
        assertThat(investorAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInvestorAddress() throws Exception {
        // Initialize the database
        investorAddressRepository.saveAndFlush(investorAddress);

        int databaseSizeBeforeDelete = investorAddressRepository.findAll().size();

        // Delete the investorAddress
        restInvestorAddressMockMvc.perform(delete("/api/investor-addresses/{id}", investorAddress.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InvestorAddress> investorAddressList = investorAddressRepository.findAll();
        assertThat(investorAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAddress.class);
        InvestorAddress investorAddress1 = new InvestorAddress();
        investorAddress1.setId(1L);
        InvestorAddress investorAddress2 = new InvestorAddress();
        investorAddress2.setId(investorAddress1.getId());
        assertThat(investorAddress1).isEqualTo(investorAddress2);
        investorAddress2.setId(2L);
        assertThat(investorAddress1).isNotEqualTo(investorAddress2);
        investorAddress1.setId(null);
        assertThat(investorAddress1).isNotEqualTo(investorAddress2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestorAddressDTO.class);
        InvestorAddressDTO investorAddressDTO1 = new InvestorAddressDTO();
        investorAddressDTO1.setId(1L);
        InvestorAddressDTO investorAddressDTO2 = new InvestorAddressDTO();
        assertThat(investorAddressDTO1).isNotEqualTo(investorAddressDTO2);
        investorAddressDTO2.setId(investorAddressDTO1.getId());
        assertThat(investorAddressDTO1).isEqualTo(investorAddressDTO2);
        investorAddressDTO2.setId(2L);
        assertThat(investorAddressDTO1).isNotEqualTo(investorAddressDTO2);
        investorAddressDTO1.setId(null);
        assertThat(investorAddressDTO1).isNotEqualTo(investorAddressDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investorAddressMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investorAddressMapper.fromId(null)).isNull();
    }
}
