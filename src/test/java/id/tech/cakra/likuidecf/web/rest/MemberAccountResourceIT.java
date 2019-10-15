package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.LikuidecfApp;
import id.tech.cakra.likuidecf.domain.MemberAccount;
import id.tech.cakra.likuidecf.repository.MemberAccountRepository;
import id.tech.cakra.likuidecf.service.MemberAccountService;
import id.tech.cakra.likuidecf.service.dto.MemberAccountDTO;
import id.tech.cakra.likuidecf.service.mapper.MemberAccountMapper;
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
 * Integration tests for the {@link MemberAccountResource} REST controller.
 */
@SpringBootTest(classes = LikuidecfApp.class)
public class MemberAccountResourceIT {

    private static final String DEFAULT_MEMBER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_MEMBER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MEMBER_SINCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MEMBER_SINCE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_MEMBER_STATUS = 1;
    private static final Integer UPDATED_MEMBER_STATUS = 2;

    @Autowired
    private MemberAccountRepository memberAccountRepository;

    @Autowired
    private MemberAccountMapper memberAccountMapper;

    @Autowired
    private MemberAccountService memberAccountService;

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

    private MockMvc restMemberAccountMockMvc;

    private MemberAccount memberAccount;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MemberAccountResource memberAccountResource = new MemberAccountResource(memberAccountService);
        this.restMemberAccountMockMvc = MockMvcBuilders.standaloneSetup(memberAccountResource)
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
    public static MemberAccount createEntity(EntityManager em) {
        MemberAccount memberAccount = new MemberAccount()
            .memberEmail(DEFAULT_MEMBER_EMAIL)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .memberSince(DEFAULT_MEMBER_SINCE)
            .memberStatus(DEFAULT_MEMBER_STATUS);
        return memberAccount;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MemberAccount createUpdatedEntity(EntityManager em) {
        MemberAccount memberAccount = new MemberAccount()
            .memberEmail(UPDATED_MEMBER_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .memberSince(UPDATED_MEMBER_SINCE)
            .memberStatus(UPDATED_MEMBER_STATUS);
        return memberAccount;
    }

    @BeforeEach
    public void initTest() {
        memberAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createMemberAccount() throws Exception {
        int databaseSizeBeforeCreate = memberAccountRepository.findAll().size();

        // Create the MemberAccount
        MemberAccountDTO memberAccountDTO = memberAccountMapper.toDto(memberAccount);
        restMemberAccountMockMvc.perform(post("/api/member-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(memberAccountDTO)))
            .andExpect(status().isCreated());

        // Validate the MemberAccount in the database
        List<MemberAccount> memberAccountList = memberAccountRepository.findAll();
        assertThat(memberAccountList).hasSize(databaseSizeBeforeCreate + 1);
        MemberAccount testMemberAccount = memberAccountList.get(memberAccountList.size() - 1);
        assertThat(testMemberAccount.getMemberEmail()).isEqualTo(DEFAULT_MEMBER_EMAIL);
        assertThat(testMemberAccount.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testMemberAccount.getMemberSince()).isEqualTo(DEFAULT_MEMBER_SINCE);
        assertThat(testMemberAccount.getMemberStatus()).isEqualTo(DEFAULT_MEMBER_STATUS);
    }

    @Test
    @Transactional
    public void createMemberAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = memberAccountRepository.findAll().size();

        // Create the MemberAccount with an existing ID
        memberAccount.setId(1L);
        MemberAccountDTO memberAccountDTO = memberAccountMapper.toDto(memberAccount);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMemberAccountMockMvc.perform(post("/api/member-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(memberAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MemberAccount in the database
        List<MemberAccount> memberAccountList = memberAccountRepository.findAll();
        assertThat(memberAccountList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMemberAccounts() throws Exception {
        // Initialize the database
        memberAccountRepository.saveAndFlush(memberAccount);

        // Get all the memberAccountList
        restMemberAccountMockMvc.perform(get("/api/member-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(memberAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].memberEmail").value(hasItem(DEFAULT_MEMBER_EMAIL)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].memberSince").value(hasItem(DEFAULT_MEMBER_SINCE.toString())))
            .andExpect(jsonPath("$.[*].memberStatus").value(hasItem(DEFAULT_MEMBER_STATUS)));
    }
    
    @Test
    @Transactional
    public void getMemberAccount() throws Exception {
        // Initialize the database
        memberAccountRepository.saveAndFlush(memberAccount);

        // Get the memberAccount
        restMemberAccountMockMvc.perform(get("/api/member-accounts/{id}", memberAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(memberAccount.getId().intValue()))
            .andExpect(jsonPath("$.memberEmail").value(DEFAULT_MEMBER_EMAIL))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.memberSince").value(DEFAULT_MEMBER_SINCE.toString()))
            .andExpect(jsonPath("$.memberStatus").value(DEFAULT_MEMBER_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingMemberAccount() throws Exception {
        // Get the memberAccount
        restMemberAccountMockMvc.perform(get("/api/member-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMemberAccount() throws Exception {
        // Initialize the database
        memberAccountRepository.saveAndFlush(memberAccount);

        int databaseSizeBeforeUpdate = memberAccountRepository.findAll().size();

        // Update the memberAccount
        MemberAccount updatedMemberAccount = memberAccountRepository.findById(memberAccount.getId()).get();
        // Disconnect from session so that the updates on updatedMemberAccount are not directly saved in db
        em.detach(updatedMemberAccount);
        updatedMemberAccount
            .memberEmail(UPDATED_MEMBER_EMAIL)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .memberSince(UPDATED_MEMBER_SINCE)
            .memberStatus(UPDATED_MEMBER_STATUS);
        MemberAccountDTO memberAccountDTO = memberAccountMapper.toDto(updatedMemberAccount);

        restMemberAccountMockMvc.perform(put("/api/member-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(memberAccountDTO)))
            .andExpect(status().isOk());

        // Validate the MemberAccount in the database
        List<MemberAccount> memberAccountList = memberAccountRepository.findAll();
        assertThat(memberAccountList).hasSize(databaseSizeBeforeUpdate);
        MemberAccount testMemberAccount = memberAccountList.get(memberAccountList.size() - 1);
        assertThat(testMemberAccount.getMemberEmail()).isEqualTo(UPDATED_MEMBER_EMAIL);
        assertThat(testMemberAccount.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testMemberAccount.getMemberSince()).isEqualTo(UPDATED_MEMBER_SINCE);
        assertThat(testMemberAccount.getMemberStatus()).isEqualTo(UPDATED_MEMBER_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingMemberAccount() throws Exception {
        int databaseSizeBeforeUpdate = memberAccountRepository.findAll().size();

        // Create the MemberAccount
        MemberAccountDTO memberAccountDTO = memberAccountMapper.toDto(memberAccount);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMemberAccountMockMvc.perform(put("/api/member-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(memberAccountDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MemberAccount in the database
        List<MemberAccount> memberAccountList = memberAccountRepository.findAll();
        assertThat(memberAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMemberAccount() throws Exception {
        // Initialize the database
        memberAccountRepository.saveAndFlush(memberAccount);

        int databaseSizeBeforeDelete = memberAccountRepository.findAll().size();

        // Delete the memberAccount
        restMemberAccountMockMvc.perform(delete("/api/member-accounts/{id}", memberAccount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MemberAccount> memberAccountList = memberAccountRepository.findAll();
        assertThat(memberAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberAccount.class);
        MemberAccount memberAccount1 = new MemberAccount();
        memberAccount1.setId(1L);
        MemberAccount memberAccount2 = new MemberAccount();
        memberAccount2.setId(memberAccount1.getId());
        assertThat(memberAccount1).isEqualTo(memberAccount2);
        memberAccount2.setId(2L);
        assertThat(memberAccount1).isNotEqualTo(memberAccount2);
        memberAccount1.setId(null);
        assertThat(memberAccount1).isNotEqualTo(memberAccount2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MemberAccountDTO.class);
        MemberAccountDTO memberAccountDTO1 = new MemberAccountDTO();
        memberAccountDTO1.setId(1L);
        MemberAccountDTO memberAccountDTO2 = new MemberAccountDTO();
        assertThat(memberAccountDTO1).isNotEqualTo(memberAccountDTO2);
        memberAccountDTO2.setId(memberAccountDTO1.getId());
        assertThat(memberAccountDTO1).isEqualTo(memberAccountDTO2);
        memberAccountDTO2.setId(2L);
        assertThat(memberAccountDTO1).isNotEqualTo(memberAccountDTO2);
        memberAccountDTO1.setId(null);
        assertThat(memberAccountDTO1).isNotEqualTo(memberAccountDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(memberAccountMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(memberAccountMapper.fromId(null)).isNull();
    }
}
