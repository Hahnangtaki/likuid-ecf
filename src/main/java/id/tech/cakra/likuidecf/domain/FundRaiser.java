package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FundRaiser.
 */
@Entity
@Table(name = "fund_raiser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FundRaiser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "year_founded")
    private Integer yearFounded;

    @Size(max = 100)
    @Column(name = "website", length = 100)
    private String website;

    @OneToMany(mappedBy = "fundRaiser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FundRaiserBank> fundRaiserBanks = new HashSet<>();

    @OneToMany(mappedBy = "fundRaiser")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Campaign> campaigns = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public FundRaiser companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public FundRaiser yearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
        return this;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getWebsite() {
        return website;
    }

    public FundRaiser website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Set<FundRaiserBank> getFundRaiserBanks() {
        return fundRaiserBanks;
    }

    public FundRaiser fundRaiserBanks(Set<FundRaiserBank> fundRaiserBanks) {
        this.fundRaiserBanks = fundRaiserBanks;
        return this;
    }

    public FundRaiser addFundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBanks.add(fundRaiserBank);
        fundRaiserBank.setFundRaiser(this);
        return this;
    }

    public FundRaiser removeFundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBanks.remove(fundRaiserBank);
        fundRaiserBank.setFundRaiser(null);
        return this;
    }

    public void setFundRaiserBanks(Set<FundRaiserBank> fundRaiserBanks) {
        this.fundRaiserBanks = fundRaiserBanks;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public FundRaiser campaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    public FundRaiser addCampaign(Campaign campaign) {
        this.campaigns.add(campaign);
        campaign.setFundRaiser(this);
        return this;
    }

    public FundRaiser removeCampaign(Campaign campaign) {
        this.campaigns.remove(campaign);
        campaign.setFundRaiser(null);
        return this;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundRaiser)) {
            return false;
        }
        return id != null && id.equals(((FundRaiser) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FundRaiser{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", yearFounded=" + getYearFounded() +
            ", website='" + getWebsite() + "'" +
            "}";
    }
}
