package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CompanyBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CompanyBank} and its DTO {@link CompanyBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class, CurrencyMapper.class})
public interface CompanyBankMapper extends EntityMapper<CompanyBankDTO, CompanyBank> {

    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "currency.id", target = "currencyId")
    CompanyBankDTO toDto(CompanyBank companyBank);

    @Mapping(target = "campaignPayments", ignore = true)
    @Mapping(target = "removeCampaignPayment", ignore = true)
    @Mapping(source = "bankId", target = "bank")
    @Mapping(source = "currencyId", target = "currency")
    CompanyBank toEntity(CompanyBankDTO companyBankDTO);

    default CompanyBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyBank companyBank = new CompanyBank();
        companyBank.setId(id);
        return companyBank;
    }
}
