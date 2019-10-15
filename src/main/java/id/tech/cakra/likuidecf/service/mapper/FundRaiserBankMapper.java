package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.FundRaiserBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FundRaiserBank} and its DTO {@link FundRaiserBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {FundRaiserMapper.class, BankMapper.class, CurrencyMapper.class})
public interface FundRaiserBankMapper extends EntityMapper<FundRaiserBankDTO, FundRaiserBank> {

    @Mapping(source = "fundRaiser.id", target = "fundRaiserId")
    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "currency.id", target = "currencyId")
    FundRaiserBankDTO toDto(FundRaiserBank fundRaiserBank);

    @Mapping(target = "campaignPayments", ignore = true)
    @Mapping(target = "removeCampaignPayment", ignore = true)
    @Mapping(source = "fundRaiserId", target = "fundRaiser")
    @Mapping(source = "bankId", target = "bank")
    @Mapping(source = "currencyId", target = "currency")
    FundRaiserBank toEntity(FundRaiserBankDTO fundRaiserBankDTO);

    default FundRaiserBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        FundRaiserBank fundRaiserBank = new FundRaiserBank();
        fundRaiserBank.setId(id);
        return fundRaiserBank;
    }
}
