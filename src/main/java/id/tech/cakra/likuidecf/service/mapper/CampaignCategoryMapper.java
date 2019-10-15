package id.tech.cakra.likuidecf.service.mapper;

import id.tech.cakra.likuidecf.domain.*;
import id.tech.cakra.likuidecf.service.dto.CampaignCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CampaignCategory} and its DTO {@link CampaignCategoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoryMapper.class, CampaignMapper.class})
public interface CampaignCategoryMapper extends EntityMapper<CampaignCategoryDTO, CampaignCategory> {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "campaign.id", target = "campaignId")
    CampaignCategoryDTO toDto(CampaignCategory campaignCategory);

    @Mapping(source = "categoryId", target = "category")
    @Mapping(source = "campaignId", target = "campaign")
    CampaignCategory toEntity(CampaignCategoryDTO campaignCategoryDTO);

    default CampaignCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampaignCategory campaignCategory = new CampaignCategory();
        campaignCategory.setId(id);
        return campaignCategory;
    }
}
