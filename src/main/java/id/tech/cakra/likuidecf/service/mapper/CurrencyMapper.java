package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CurrencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Currency} and its DTO {@link CurrencyDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {


    @Mapping(target = "fundRaiserBanks", ignore = true)
    @Mapping(target = "removeFundRaiserBank", ignore = true)
    @Mapping(target = "companyBanks", ignore = true)
    @Mapping(target = "removeCompanyBank", ignore = true)
    @Mapping(target = "investorBanks", ignore = true)
    @Mapping(target = "removeInvestorBank", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "removeCampaign", ignore = true)
    Currency toEntity(CurrencyDTO currencyDTO);

    default Currency fromId(Long id) {
        if (id == null) {
            return null;
        }
        Currency currency = new Currency();
        currency.setId(id);
        return currency;
    }
}
