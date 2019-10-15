package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProvinceMapper.class})
public interface CityMapper extends EntityMapper<CityDTO, City> {

    @Mapping(source = "province.id", target = "provinceId")
    CityDTO toDto(City city);

    @Mapping(target = "investorAddresses", ignore = true)
    @Mapping(target = "removeInvestorAddress", ignore = true)
    @Mapping(source = "provinceId", target = "province")
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}
