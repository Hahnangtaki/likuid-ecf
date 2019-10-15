package id.tech.cakra.likuidecf.service.impl;

import id.tech.cakra.likuidecf.service.MemberAccountService;
import id.tech.cakra.likuidecf.domain.MemberAccount;
import id.tech.cakra.likuidecf.repository.MemberAccountRepository;
import id.tech.cakra.likuidecf.service.dto.MemberAccountDTO;
import id.tech.cakra.likuidecf.service.mapper.MemberAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link MemberAccount}.
 */
@Service
@Transactional
public class MemberAccountServiceImpl implements MemberAccountService {

    private final Logger log = LoggerFactory.getLogger(MemberAccountServiceImpl.class);

    private final MemberAccountRepository memberAccountRepository;

    private final MemberAccountMapper memberAccountMapper;

    public MemberAccountServiceImpl(MemberAccountRepository memberAccountRepository, MemberAccountMapper memberAccountMapper) {
        this.memberAccountRepository = memberAccountRepository;
        this.memberAccountMapper = memberAccountMapper;
    }

    /**
     * Save a memberAccount.
     *
     * @param memberAccountDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MemberAccountDTO save(MemberAccountDTO memberAccountDTO) {
        log.debug("Request to save MemberAccount : {}", memberAccountDTO);
        MemberAccount memberAccount = memberAccountMapper.toEntity(memberAccountDTO);
        memberAccount = memberAccountRepository.save(memberAccount);
        return memberAccountMapper.toDto(memberAccount);
    }

    /**
     * Get all the memberAccounts.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MemberAccountDTO> findAll() {
        log.debug("Request to get all MemberAccounts");
        return memberAccountRepository.findAll().stream()
            .map(memberAccountMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one memberAccount by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MemberAccountDTO> findOne(Long id) {
        log.debug("Request to get MemberAccount : {}", id);
        return memberAccountRepository.findById(id)
            .map(memberAccountMapper::toDto);
    }

    /**
     * Delete the memberAccount by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MemberAccount : {}", id);
        memberAccountRepository.deleteById(id);
    }
}
