package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CampaignInvestorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignInvestor} and its DTO {@link CampaignInvestorDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvestorMapper.class, CampaignMapper.class})
public interface CampaignInvestorMapper extends EntityMapper<CampaignInvestorDTO, CampaignInvestor> {

    @Mapping(source = "investor.id", target = "investorId")
    @Mapping(source = "campaign.id", target = "campaignId")
    CampaignInvestorDTO toDto(CampaignInvestor campaignInvestor);

    @Mapping(source = "investorId", target = "investor")
    @Mapping(source = "campaignId", target = "campaign")
    CampaignInvestor toEntity(CampaignInvestorDTO campaignInvestorDTO);

    default CampaignInvestor fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignInvestor campaignInvestor = new CampaignInvestor();
        campaignInvestor.setId(id);
        return campaignInvestor;
    }
}
