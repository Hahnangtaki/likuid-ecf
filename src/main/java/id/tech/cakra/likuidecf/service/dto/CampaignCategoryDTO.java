package id.tech.cakra.likuidecf.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.CampaignCategory} entity.
 */
public class CampaignCategoryDTO implements Serializable {

    private Long id;


    private Long categoryId;

    private Long campaignId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CampaignCategoryDTO campaignCategoryDTO = (CampaignCategoryDTO) o;
        if (campaignCategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignCategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignCategoryDTO{" +
            "id=" + getId() +
            ", category=" + getCategoryId() +
            ", campaign=" + getCampaignId() +
            "}";
    }
}
