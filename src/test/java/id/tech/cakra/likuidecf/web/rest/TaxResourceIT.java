package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.Tax;
import id.tech.cakra.likuidecf.repository.TaxRepository;
import id.tech.cakra.likuidecf.service.TaxService;
import id.tech.cakra.likuidecf.service.dto.TaxDTO;
import id.tech.cakra.likuidecf.service.mapper.TaxMapper;
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
 * Integration tests for the {@link TaxResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class TaxResourceIT {

    private static final String DEFAULT_TAX_CODE = "AAAAAAAAAA";
    private static final String UPDATED_TAX_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SHORT_DESC = "AAAAAAAAAA";
    private static final String UPDATED_SHORT_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_LONG_DESC = "AAAAAAAAAA";
    private static final String UPDATED_LONG_DESC = "BBBBBBBBBB";

    @Autowired
    private TaxRepository taxRepository;

    @Autowired
    private TaxMapper taxMapper;

    @Autowired
    private TaxService taxService;

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

    private MockMvc restTaxMockMvc;

    private Tax tax;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TaxResource taxResource = new TaxResource(taxService);
        this.restTaxMockMvc = MockMvcBuilders.standaloneSetup(taxResource)
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
    public static Tax createEntity(EntityManager em) {
        Tax tax = new Tax()
            .taxCode(DEFAULT_TAX_CODE)
            .shortDesc(DEFAULT_SHORT_DESC)
            .longDesc(DEFAULT_LONG_DESC);
        return tax;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tax createUpdatedEntity(EntityManager em) {
        Tax tax = new Tax()
            .taxCode(UPDATED_TAX_CODE)
            .shortDesc(UPDATED_SHORT_DESC)
            .longDesc(UPDATED_LONG_DESC);
        return tax;
    }

    @BeforeEach
    public void initTest() {
        tax = createEntity(em);
    }

    @Test
    @Transactional
    public void createTax() throws Exception {
        int databaseSizeBeforeCreate = taxRepository.findAll().size();

        // Create the Tax
        TaxDTO taxDTO = taxMapper.toDto(tax);
        restTaxMockMvc.perform(post("/api/taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isCreated());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeCreate + 1);
        Tax testTax = taxList.get(taxList.size() - 1);
        assertThat(testTax.getTaxCode()).isEqualTo(DEFAULT_TAX_CODE);
        assertThat(testTax.getShortDesc()).isEqualTo(DEFAULT_SHORT_DESC);
        assertThat(testTax.getLongDesc()).isEqualTo(DEFAULT_LONG_DESC);
    }

    @Test
    @Transactional
    public void createTaxWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taxRepository.findAll().size();

        // Create the Tax with an existing ID
        tax.setId(1L);
        TaxDTO taxDTO = taxMapper.toDto(tax);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxMockMvc.perform(post("/api/taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTaxes() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        // Get all the taxList
        restTaxMockMvc.perform(get("/api/taxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tax.getId().intValue())))
            .andExpect(jsonPath("$.[*].taxCode").value(hasItem(DEFAULT_TAX_CODE)))
            .andExpect(jsonPath("$.[*].shortDesc").value(hasItem(DEFAULT_SHORT_DESC)))
            .andExpect(jsonPath("$.[*].longDesc").value(hasItem(DEFAULT_LONG_DESC)));
    }
    
    @Test
    @Transactional
    public void getTax() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        // Get the tax
        restTaxMockMvc.perform(get("/api/taxes/{id}", tax.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tax.getId().intValue()))
            .andExpect(jsonPath("$.taxCode").value(DEFAULT_TAX_CODE))
            .andExpect(jsonPath("$.shortDesc").value(DEFAULT_SHORT_DESC))
            .andExpect(jsonPath("$.longDesc").value(DEFAULT_LONG_DESC));
    }

    @Test
    @Transactional
    public void getNonExistingTax() throws Exception {
        // Get the tax
        restTaxMockMvc.perform(get("/api/taxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTax() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        int databaseSizeBeforeUpdate = taxRepository.findAll().size();

        // Update the tax
        Tax updatedTax = taxRepository.findById(tax.getId()).get();
        // Disconnect from session so that the updates on updatedTax are not directly saved in db
        em.detach(updatedTax);
        updatedTax
            .taxCode(UPDATED_TAX_CODE)
            .shortDesc(UPDATED_SHORT_DESC)
            .longDesc(UPDATED_LONG_DESC);
        TaxDTO taxDTO = taxMapper.toDto(updatedTax);

        restTaxMockMvc.perform(put("/api/taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isOk());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeUpdate);
        Tax testTax = taxList.get(taxList.size() - 1);
        assertThat(testTax.getTaxCode()).isEqualTo(UPDATED_TAX_CODE);
        assertThat(testTax.getShortDesc()).isEqualTo(UPDATED_SHORT_DESC);
        assertThat(testTax.getLongDesc()).isEqualTo(UPDATED_LONG_DESC);
    }

    @Test
    @Transactional
    public void updateNonExistingTax() throws Exception {
        int databaseSizeBeforeUpdate = taxRepository.findAll().size();

        // Create the Tax
        TaxDTO taxDTO = taxMapper.toDto(tax);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxMockMvc.perform(put("/api/taxes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tax in the database
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTax() throws Exception {
        // Initialize the database
        taxRepository.saveAndFlush(tax);

        int databaseSizeBeforeDelete = taxRepository.findAll().size();

        // Delete the tax
        restTaxMockMvc.perform(delete("/api/taxes/{id}", tax.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tax> taxList = taxRepository.findAll();
        assertThat(taxList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tax.class);
        Tax tax1 = new Tax();
        tax1.setId(1L);
        Tax tax2 = new Tax();
        tax2.setId(tax1.getId());
        assertThat(tax1).isEqualTo(tax2);
        tax2.setId(2L);
        assertThat(tax1).isNotEqualTo(tax2);
        tax1.setId(null);
        assertThat(tax1).isNotEqualTo(tax2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxDTO.class);
        TaxDTO taxDTO1 = new TaxDTO();
        taxDTO1.setId(1L);
        TaxDTO taxDTO2 = new TaxDTO();
        assertThat(taxDTO1).isNotEqualTo(taxDTO2);
        taxDTO2.setId(taxDTO1.getId());
        assertThat(taxDTO1).isEqualTo(taxDTO2);
        taxDTO2.setId(2L);
        assertThat(taxDTO1).isNotEqualTo(taxDTO2);
        taxDTO1.setId(null);
        assertThat(taxDTO1).isNotEqualTo(taxDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(taxMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(taxMapper.fromId(null)).isNull();
    }
}
