package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.BankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Bank} and its DTO {@link BankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BankMapper extends EntityMapper<BankDTO, Bank> {


    @Mapping(target = "fundRaiserBanks", ignore = true)
    @Mapping(target = "removeFundRaiserBank", ignore = true)
    @Mapping(target = "companyBanks", ignore = true)
    @Mapping(target = "removeCompanyBank", ignore = true)
    @Mapping(target = "investorBanks", ignore = true)
    @Mapping(target = "removeInvestorBank", ignore = true)
    Bank toEntity(BankDTO bankDTO);

    default Bank fromId(Long id) {
        if (id == null) {
            return null;
        }
        Bank bank = new Bank();
        bank.setId(id);
        return bank;
    }
}
