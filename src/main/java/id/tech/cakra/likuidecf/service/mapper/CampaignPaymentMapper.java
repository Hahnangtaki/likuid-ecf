package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CampaignPaymentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignPayment} and its DTO {@link CampaignPaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {FundRaiserBankMapper.class, CampaignMapper.class, CompanyBankMapper.class})
public interface CampaignPaymentMapper extends EntityMapper<CampaignPaymentDTO, CampaignPayment> {

    @Mapping(source = "fundRaiserBank.id", target = "fundRaiserBankId")
    @Mapping(source = "campaign.id", target = "campaignId")
    @Mapping(source = "companyBank.id", target = "companyBankId")
    CampaignPaymentDTO toDto(CampaignPayment campaignPayment);

    @Mapping(source = "fundRaiserBankId", target = "fundRaiserBank")
    @Mapping(source = "campaignId", target = "campaign")
    @Mapping(source = "companyBankId", target = "companyBank")
    CampaignPayment toEntity(CampaignPaymentDTO campaignPaymentDTO);

    default CampaignPayment fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignPayment campaignPayment = new CampaignPayment();
        campaignPayment.setId(id);
        return campaignPayment;
    }
}
