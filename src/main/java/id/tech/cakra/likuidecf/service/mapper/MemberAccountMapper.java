package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.MemberAccountDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MemberAccount} and its DTO {@link MemberAccountDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MemberAccountMapper extends EntityMapper<MemberAccountDTO, MemberAccount> {


    @Mapping(target = "otpHistories", ignore = true)
    @Mapping(target = "removeOtpHistory", ignore = true)
    MemberAccount toEntity(MemberAccountDTO memberAccountDTO);

    default MemberAccount fromId(Long id) {
        if (id == null) {
            return null;
        }
        MemberAccount memberAccount = new MemberAccount();
        memberAccount.setId(id);
        return memberAccount;
    }
}
