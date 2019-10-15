package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Investor.
 */
@Entity
@Table(name = "investor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Investor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "investor_code", length = 10)
    private String investorCode;

    @Size(max = 100)
    @Column(name = "investor_name", length = 100)
    private String investorName;

    @Size(max = 1)
    @Column(name = "investor_type", length = 1)
    private String investorType;

    @OneToOne
    @JoinColumn(unique = true)
    private InvestorInstitution investorInstitution;

    @OneToOne
    @JoinColumn(unique = true)
    private InvestorIndividu investorIndividu;

    @OneToMany(mappedBy = "investor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorBank> investorBanks = new HashSet<>();

    @OneToMany(mappedBy = "investor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorAddress> investorAddresses = new HashSet<>();

    @OneToMany(mappedBy = "investor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorAuthorizePerson> investorAuthorizePeople = new HashSet<>();

    @OneToMany(mappedBy = "investor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignInvestor> campaignInvestors = new HashSet<>();

    @OneToMany(mappedBy = "investor")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignTransaction> campaignTransactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvestorCode() {
        return investorCode;
    }

    public Investor investorCode(String investorCode) {
        this.investorCode = investorCode;
        return this;
    }

    public void setInvestorCode(String investorCode) {
        this.investorCode = investorCode;
    }

    public String getInvestorName() {
        return investorName;
    }

    public Investor investorName(String investorName) {
        this.investorName = investorName;
        return this;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorType() {
        return investorType;
    }

    public Investor investorType(String investorType) {
        this.investorType = investorType;
        return this;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public InvestorInstitution getInvestorInstitution() {
        return investorInstitution;
    }

    public Investor investorInstitution(InvestorInstitution investorInstitution) {
        this.investorInstitution = investorInstitution;
        return this;
    }

    public void setInvestorInstitution(InvestorInstitution investorInstitution) {
        this.investorInstitution = investorInstitution;
    }

    public InvestorIndividu getInvestorIndividu() {
        return investorIndividu;
    }

    public Investor investorIndividu(InvestorIndividu investorIndividu) {
        this.investorIndividu = investorIndividu;
        return this;
    }

    public void setInvestorIndividu(InvestorIndividu investorIndividu) {
        this.investorIndividu = investorIndividu;
    }

    public Set<InvestorBank> getInvestorBanks() {
        return investorBanks;
    }

    public Investor investorBanks(Set<InvestorBank> investorBanks) {
        this.investorBanks = investorBanks;
        return this;
    }

    public Investor addInvestorBank(InvestorBank investorBank) {
        this.investorBanks.add(investorBank);
        investorBank.setInvestor(this);
        return this;
    }

    public Investor removeInvestorBank(InvestorBank investorBank) {
        this.investorBanks.remove(investorBank);
        investorBank.setInvestor(null);
        return this;
    }

    public void setInvestorBanks(Set<InvestorBank> investorBanks) {
        this.investorBanks = investorBanks;
    }

    public Set<InvestorAddress> getInvestorAddresses() {
        return investorAddresses;
    }

    public Investor investorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
        return this;
    }

    public Investor addInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.add(investorAddress);
        investorAddress.setInvestor(this);
        return this;
    }

    public Investor removeInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.remove(investorAddress);
        investorAddress.setInvestor(null);
        return this;
    }

    public void setInvestorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
    }

    public Set<InvestorAuthorizePerson> getInvestorAuthorizePeople() {
        return investorAuthorizePeople;
    }

    public Investor investorAuthorizePeople(Set<InvestorAuthorizePerson> investorAuthorizePeople) {
        this.investorAuthorizePeople = investorAuthorizePeople;
        return this;
    }

    public Investor addInvestorAuthorizePerson(InvestorAuthorizePerson investorAuthorizePerson) {
        this.investorAuthorizePeople.add(investorAuthorizePerson);
        investorAuthorizePerson.setInvestor(this);
        return this;
    }

    public Investor removeInvestorAuthorizePerson(InvestorAuthorizePerson investorAuthorizePerson) {
        this.investorAuthorizePeople.remove(investorAuthorizePerson);
        investorAuthorizePerson.setInvestor(null);
        return this;
    }

    public void setInvestorAuthorizePeople(Set<InvestorAuthorizePerson> investorAuthorizePeople) {
        this.investorAuthorizePeople = investorAuthorizePeople;
    }

    public Set<CampaignInvestor> getCampaignInvestors() {
        return campaignInvestors;
    }

    public Investor campaignInvestors(Set<CampaignInvestor> campaignInvestors) {
        this.campaignInvestors = campaignInvestors;
        return this;
    }

    public Investor addCampaignInvestor(CampaignInvestor campaignInvestor) {
        this.campaignInvestors.add(campaignInvestor);
        campaignInvestor.setInvestor(this);
        return this;
    }

    public Investor removeCampaignInvestor(CampaignInvestor campaignInvestor) {
        this.campaignInvestors.remove(campaignInvestor);
        campaignInvestor.setInvestor(null);
        return this;
    }

    public void setCampaignInvestors(Set<CampaignInvestor> campaignInvestors) {
        this.campaignInvestors = campaignInvestors;
    }

    public Set<CampaignTransaction> getCampaignTransactions() {
        return campaignTransactions;
    }

    public Investor campaignTransactions(Set<CampaignTransaction> campaignTransactions) {
        this.campaignTransactions = campaignTransactions;
        return this;
    }

    public Investor addCampaignTransaction(CampaignTransaction campaignTransaction) {
        this.campaignTransactions.add(campaignTransaction);
        campaignTransaction.setInvestor(this);
        return this;
    }

    public Investor removeCampaignTransaction(CampaignTransaction campaignTransaction) {
        this.campaignTransactions.remove(campaignTransaction);
        campaignTransaction.setInvestor(null);
        return this;
    }

    public void setCampaignTransactions(Set<CampaignTransaction> campaignTransactions) {
        this.campaignTransactions = campaignTransactions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Investor)) {
            return false;
        }
        return id != null && id.equals(((Investor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Investor{" +
            "id=" + getId() +
            ", investorCode='" + getInvestorCode() + "'" +
            ", investorName='" + getInvestorName() + "'" +
            ", investorType='" + getInvestorType() + "'" +
            "}";
    }
}
