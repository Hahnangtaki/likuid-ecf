package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.InvestorIndividuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestorIndividu} and its DTO {@link InvestorIndividuDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaxMapper.class})
public interface InvestorIndividuMapper extends EntityMapper<InvestorIndividuDTO, InvestorIndividu> {

    @Mapping(source = "tax.id", target = "taxId")
    InvestorIndividuDTO toDto(InvestorIndividu investorIndividu);

    @Mapping(target = "investor", ignore = true)
    @Mapping(source = "taxId", target = "tax")
    InvestorIndividu toEntity(InvestorIndividuDTO investorIndividuDTO);

    default InvestorIndividu fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestorIndividu investorIndividu = new InvestorIndividu();
        investorIndividu.setId(id);
        return investorIndividu;
    }
}
