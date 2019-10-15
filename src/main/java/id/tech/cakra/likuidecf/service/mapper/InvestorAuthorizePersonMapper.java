package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.InvestorAuthorizePersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestorAuthorizePerson} and its DTO {@link InvestorAuthorizePersonDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvestorMapper.class})
public interface InvestorAuthorizePersonMapper extends EntityMapper<InvestorAuthorizePersonDTO, InvestorAuthorizePerson> {

    @Mapping(source = "investor.id", target = "investorId")
    InvestorAuthorizePersonDTO toDto(InvestorAuthorizePerson investorAuthorizePerson);

    @Mapping(source = "investorId", target = "investor")
    InvestorAuthorizePerson toEntity(InvestorAuthorizePersonDTO investorAuthorizePersonDTO);

    default InvestorAuthorizePerson fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestorAuthorizePerson investorAuthorizePerson = new InvestorAuthorizePerson();
        investorAuthorizePerson.setId(id);
        return investorAuthorizePerson;
    }
}
