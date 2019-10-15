package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.InvestorBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestorBank} and its DTO {@link InvestorBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {BankMapper.class, CurrencyMapper.class, InvestorMapper.class})
public interface InvestorBankMapper extends EntityMapper<InvestorBankDTO, InvestorBank> {

    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "currency.id", target = "currencyId")
    @Mapping(source = "investor.id", target = "investorId")
    InvestorBankDTO toDto(InvestorBank investorBank);

    @Mapping(source = "bankId", target = "bank")
    @Mapping(source = "currencyId", target = "currency")
    @Mapping(source = "investorId", target = "investor")
    InvestorBank toEntity(InvestorBankDTO investorBankDTO);

    default InvestorBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestorBank investorBank = new InvestorBank();
        investorBank.setId(id);
        return investorBank;
    }
}
