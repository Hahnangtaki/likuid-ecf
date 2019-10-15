package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CampaignDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Campaign} and its DTO {@link CampaignDTO}.
 */
@Mapper(componentModel = "spring", uses = {CurrencyMapper.class, FundRaiserMapper.class})
public interface CampaignMapper extends EntityMapper<CampaignDTO, Campaign> {

    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "fundRaiser.id", target = "fundRaiserId")
    CampaignDTO toDto(Campaign campaign);

    @Mapping(target = "campaignPayments", ignore = true)
    @Mapping(target = "removeCampaignPayment", ignore = true)
    @Mapping(target = "campaignInvestors", ignore = true)
    @Mapping(target = "removeCampaignInvestor", ignore = true)
    @Mapping(target = "campaignTransactions", ignore = true)
    @Mapping(target = "removeCampaignTransaction", ignore = true)
    @Mapping(target = "campaignCategories", ignore = true)
    @Mapping(target = "removeCampaignCategory", ignore = true)
    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "fundRaiserId", target = "fundRaiser")
    Campaign toEntity(CampaignDTO campaignDTO);

    default Campaign fromId(Long id) {
        if (id == null) {
            return null;
        }
        Campaign campaign = new Campaign();
        campaign.setId(id);
        return campaign;
    }
}
