package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CampaignTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignTransaction} and its DTO {@link CampaignTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {InvestorMapper.class, CampaignMapper.class})
public interface CampaignTransactionMapper extends EntityMapper<CampaignTransactionDTO, CampaignTransaction> {

    @Mapping(source = "investor.id", target = "investorId")
    @Mapping(source = "campaign.id", target = "campaignId")
    CampaignTransactionDTO toDto(CampaignTransaction campaignTransaction);

    @Mapping(source = "investorId", target = "investor")
    @Mapping(source = "campaignId", target = "campaign")
    CampaignTransaction toEntity(CampaignTransactionDTO campaignTransactionDTO);

    default CampaignTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignTransaction campaignTransaction = new CampaignTransaction();
        campaignTransaction.setId(id);
        return campaignTransaction;
    }
}
