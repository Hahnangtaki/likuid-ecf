package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.InvestorInstitutionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestorInstitution} and its DTO {@link InvestorInstitutionDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaxMapper.class})
public interface InvestorInstitutionMapper extends EntityMapper<InvestorInstitutionDTO, InvestorInstitution> {

    @Mapping(source = "tax.id", target = "taxId")
    InvestorInstitutionDTO toDto(InvestorInstitution investorInstitution);

    @Mapping(target = "investor", ignore = true)
    @Mapping(source = "taxId", target = "tax")
    InvestorInstitution toEntity(InvestorInstitutionDTO investorInstitutionDTO);

    default InvestorInstitution fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestorInstitution investorInstitution = new InvestorInstitution();
        investorInstitution.setId(id);
        return investorInstitution;
    }
}
