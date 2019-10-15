package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.OtpHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OtpHistory} and its DTO {@link OtpHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {MemberAccountMapper.class})
public interface OtpHistoryMapper extends EntityMapper<OtpHistoryDTO, OtpHistory> {

    @Mapping(source = "memberAccount.id", target = "memberAccountId")
    OtpHistoryDTO toDto(OtpHistory otpHistory);

    @Mapping(source = "memberAccountId", target = "memberAccount")
    OtpHistory toEntity(OtpHistoryDTO otpHistoryDTO);

    default OtpHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        OtpHistory otpHistory = new OtpHistory();
        otpHistory.setId(id);
        return otpHistory;
    }
}
