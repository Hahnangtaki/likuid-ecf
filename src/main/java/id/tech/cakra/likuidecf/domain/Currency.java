package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Currency.
 */
@Entity
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Currency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 5)
    @Column(name = "currency_code", length = 5)
    private String currencyCode;

    @Size(max = 100)
    @Column(name = "currency_name", length = 100)
    private String currencyName;

    @Size(max = 5)
    @Column(name = "currency_symbol", length = 5)
    private String currencySymbol;

    @OneToMany(mappedBy = "currency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FundRaiserBank> fundRaiserBanks = new HashSet<>();

    @OneToMany(mappedBy = "currency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CompanyBank> companyBanks = new HashSet<>();

    @OneToMany(mappedBy = "currency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorBank> investorBanks = new HashSet<>();

    @OneToMany(mappedBy = "currency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Campaign> campaigns = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Currency currencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public Currency currencyName(String currencyName) {
        this.currencyName = currencyName;
        return this;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public Currency currencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
        return this;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Set<FundRaiserBank> getFundRaiserBanks() {
        return fundRaiserBanks;
    }

    public Currency fundRaiserBanks(Set<FundRaiserBank> fundRaiserBanks) {
        this.fundRaiserBanks = fundRaiserBanks;
        return this;
    }

    public Currency addFundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBanks.add(fundRaiserBank);
        fundRaiserBank.setCurrency(this);
        return this;
    }

    public Currency removeFundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBanks.remove(fundRaiserBank);
        fundRaiserBank.setCurrency(null);
        return this;
    }

    public void setFundRaiserBanks(Set<FundRaiserBank> fundRaiserBanks) {
        this.fundRaiserBanks = fundRaiserBanks;
    }

    public Set<CompanyBank> getCompanyBanks() {
        return companyBanks;
    }

    public Currency companyBanks(Set<CompanyBank> companyBanks) {
        this.companyBanks = companyBanks;
        return this;
    }

    public Currency addCompanyBank(CompanyBank companyBank) {
        this.companyBanks.add(companyBank);
        companyBank.setCurrency(this);
        return this;
    }

    public Currency removeCompanyBank(CompanyBank companyBank) {
        this.companyBanks.remove(companyBank);
        companyBank.setCurrency(null);
        return this;
    }

    public void setCompanyBanks(Set<CompanyBank> companyBanks) {
        this.companyBanks = companyBanks;
    }

    public Set<InvestorBank> getInvestorBanks() {
        return investorBanks;
    }

    public Currency investorBanks(Set<InvestorBank> investorBanks) {
        this.investorBanks = investorBanks;
        return this;
    }

    public Currency addInvestorBank(InvestorBank investorBank) {
        this.investorBanks.add(investorBank);
        investorBank.setCurrency(this);
        return this;
    }

    public Currency removeInvestorBank(InvestorBank investorBank) {
        this.investorBanks.remove(investorBank);
        investorBank.setCurrency(null);
        return this;
    }

    public void setInvestorBanks(Set<InvestorBank> investorBanks) {
        this.investorBanks = investorBanks;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public Currency campaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    public Currency addCampaign(Campaign campaign) {
        this.campaigns.add(campaign);
        campaign.setCurrency(this);
        return this;
    }

    public Currency removeCampaign(Campaign campaign) {
        this.campaigns.remove(campaign);
        campaign.setCurrency(null);
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
        if (!(o instanceof Currency)) {
            return false;
        }
        return id != null && id.equals(((Currency) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Currency{" +
            "id=" + getId() +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", currencyName='" + getCurrencyName() + "'" +
            ", currencySymbol='" + getCurrencySymbol() + "'" +
            "}";
    }
}
