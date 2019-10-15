package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.InvestorAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InvestorAddress} and its DTO {@link InvestorAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {CityMapper.class, ProvinceMapper.class, CountryMapper.class, InvestorMapper.class})
public interface InvestorAddressMapper extends EntityMapper<InvestorAddressDTO, InvestorAddress> {

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "country.id", target = "countryId")
    @Mapping(source = "investor.id", target = "investorId")
    InvestorAddressDTO toDto(InvestorAddress investorAddress);

    @Mapping(source = "cityId", target = "city")
    @Mapping(source = "provinceId", target = "province")
    @Mapping(source = "countryId", target = "country")
    @Mapping(source = "investorId", target = "investor")
    InvestorAddress toEntity(InvestorAddressDTO investorAddressDTO);

    default InvestorAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        InvestorAddress investorAddress = new InvestorAddress();
        investorAddress.setId(id);
        return investorAddress;
    }
}
