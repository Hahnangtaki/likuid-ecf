package id.tech.cakra.likuidecf.web.rest;

import id.tech.cakra.likuidecf.service.MemberAccountService;
import id.tech.cakra.likuidecf.web.rest.errors.BadRequestAlertException;
import id.tech.cakra.likuidecf.service.dto.MemberAccountDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link id.tech.cakra.likuidecf.domain.MemberAccount}.
 */
@RestController
@RequestMapping("/api")
public class MemberAccountResource {

    private final Logger log = LoggerFactory.getLogger(MemberAccountResource.class);

    private static final String ENTITY_NAME = "memberAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MemberAccountService memberAccountService;

    public MemberAccountResource(MemberAccountService memberAccountService) {
        this.memberAccountService = memberAccountService;
    }

    /**
     * {@code POST  /member-accounts} : Create a new memberAccount.
     *
     * @param memberAccountDTO the memberAccountDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new memberAccountDTO, or with status {@code 400 (Bad Request)} if the memberAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/member-accounts")
    public ResponseEntity<MemberAccountDTO> createMemberAccount(@RequestBody MemberAccountDTO memberAccountDTO) throws URISyntaxException {
        log.debug("REST request to save MemberAccount : {}", memberAccountDTO);
        if (memberAccountDTO.getId() != null) {
            throw new BadRequestAlertException("A new memberAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MemberAccountDTO result = memberAccountService.save(memberAccountDTO);
        return ResponseEntity.created(new URI("/api/member-accounts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /member-accounts} : Updates an existing memberAccount.
     *
     * @param memberAccountDTO the memberAccountDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated memberAccountDTO,
     * or with status {@code 400 (Bad Request)} if the memberAccountDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the memberAccountDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/member-accounts")
    public ResponseEntity<MemberAccountDTO> updateMemberAccount(@RequestBody MemberAccountDTO memberAccountDTO) throws URISyntaxException {
        log.debug("REST request to update MemberAccount : {}", memberAccountDTO);
        if (memberAccountDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MemberAccountDTO result = memberAccountService.save(memberAccountDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, memberAccountDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /member-accounts} : get all the memberAccounts.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of memberAccounts in body.
     */
    @GetMapping("/member-accounts")
    public List<MemberAccountDTO> getAllMemberAccounts() {
        log.debug("REST request to get all MemberAccounts");
        return memberAccountService.findAll();
    }

    /**
     * {@code GET  /member-accounts/:id} : get the "id" memberAccount.
     *
     * @param id the id of the memberAccountDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the memberAccountDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/member-accounts/{id}")
    public ResponseEntity<MemberAccountDTO> getMemberAccount(@PathVariable Long id) {
        log.debug("REST request to get MemberAccount : {}", id);
        Optional<MemberAccountDTO> memberAccountDTO = memberAccountService.findOne(id);
        return ResponseUtil.wrapOrNotFound(memberAccountDTO);
    }

    /**
     * {@code DELETE  /member-accounts/:id} : delete the "id" memberAccount.
     *
     * @param id the id of the memberAccountDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/member-accounts/{id}")
    public ResponseEntity<Void> deleteMemberAccount(@PathVariable Long id) {
        log.debug("REST request to delete MemberAccount : {}", id);
        memberAccountService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
