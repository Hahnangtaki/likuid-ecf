package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.TaxDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tax} and its DTO {@link TaxDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaxMapper extends EntityMapper<TaxDTO, Tax> {


    @Mapping(target = "investorIndividus", ignore = true)
    @Mapping(target = "removeInvestorIndividu", ignore = true)
    @Mapping(target = "investorInstitutions", ignore = true)
    @Mapping(target = "removeInvestorInstitution", ignore = true)
    Tax toEntity(TaxDTO taxDTO);

    default Tax fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tax tax = new Tax();
        tax.setId(id);
        return tax;
    }
}
