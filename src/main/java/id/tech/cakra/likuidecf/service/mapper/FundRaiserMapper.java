package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.FundRaiserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FundRaiser} and its DTO {@link FundRaiserDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FundRaiserMapper extends EntityMapper<FundRaiserDTO, FundRaiser> {


    @Mapping(target = "fundRaiserBanks", ignore = true)
    @Mapping(target = "removeFundRaiserBank", ignore = true)
    @Mapping(target = "campaigns", ignore = true)
    @Mapping(target = "removeCampaign", ignore = true)
    FundRaiser toEntity(FundRaiserDTO fundRaiserDTO);

    default FundRaiser fromId(Long id) {
        if (id == null) {
            return null;
        }
        FundRaiser fundRaiser = new FundRaiser();
        fundRaiser.setId(id);
        return fundRaiser;
    }
}
