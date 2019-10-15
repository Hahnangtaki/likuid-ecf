package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CampaignCategory.
 */
@Entity
@Table(name = "campaign_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampaignCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("campaignCategories")
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties("campaignCategories")
    private Campaign campaign;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public CampaignCategory category(Category category) {
        this.category = category;
        return this;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignCategory campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignCategory)) {
            return false;
        }
        return id != null && id.equals(((CampaignCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampaignCategory{" +
            "id=" + getId() +
            "}";
    }
}
