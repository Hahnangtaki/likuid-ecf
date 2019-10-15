package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.InvestorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Investor} and its DTO {@link InvestorDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvestorInstitutionMapper.class, InvestorIndividuMapper.class})
public interface InvestorMapper extends EntityMapper<InvestorDTO, Investor> {

    @Mapping(source = "investorInstitution.id", target = "investorInstitutionId")
    @Mapping(source = "investorIndividu.id", target = "investorIndividuId")
    InvestorDTO toDto(Investor investor);

    @Mapping(source = "investorInstitutionId", target = "investorInstitution")
    @Mapping(source = "investorIndividuId", target = "investorIndividu")
    @Mapping(target = "investorBanks", ignore = true)
    @Mapping(target = "removeInvestorBank", ignore = true)
    @Mapping(target = "investorAddresses", ignore = true)
    @Mapping(target = "removeInvestorAddress", ignore = true)
    @Mapping(target = "investorAuthorizePeople", ignore = true)
    @Mapping(target = "removeInvestorAuthorizePerson", ignore = true)
    @Mapping(target = "campaignInvestors", ignore = true)
    @Mapping(target = "removeCampaignInvestor", ignore = true)
    @Mapping(target = "campaignTransactions", ignore = true)
    @Mapping(target = "removeCampaignTransaction", ignore = true)
    Investor toEntity(InvestorDTO investorDTO);

    default Investor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Investor investor = new Investor();
        investor.setId(id);
        return investor;
    }
}
