package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Category.
 */
@Entity
@Table(name = "category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cat_code")
    private String catCode;

    @Column(name = "cat_name")
    private String catName;

    @OneToMany(mappedBy = "category")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignCategory> campaignCategories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatCode() {
        return catCode;
    }

    public Category catCode(String catCode) {
        this.catCode = catCode;
        return this;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getCatName() {
        return catName;
    }

    public Category catName(String catName) {
        this.catName = catName;
        return this;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Set<CampaignCategory> getCampaignCategories() {
        return campaignCategories;
    }

    public Category campaignCategories(Set<CampaignCategory> campaignCategories) {
        this.campaignCategories = campaignCategories;
        return this;
    }

    public Category addCampaignCategory(CampaignCategory campaignCategory) {
        this.campaignCategories.add(campaignCategory);
        campaignCategory.setCategory(this);
        return this;
    }

    public Category removeCampaignCategory(CampaignCategory campaignCategory) {
        this.campaignCategories.remove(campaignCategory);
        campaignCategory.setCategory(null);
        return this;
    }

    public void setCampaignCategories(Set<CampaignCategory> campaignCategories) {
        this.campaignCategories = campaignCategories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        return id != null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + getId() +
            ", catCode='" + getCatCode() + "'" +
            ", catName='" + getCatName() + "'" +
            "}";
    }
}
