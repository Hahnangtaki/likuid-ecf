package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FundRaiserBank.
 */
@Entity
@Table(name = "fund_raiser_bank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FundRaiserBank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 60)
    @Column(name = "bank_account_no", length = 60)
    private String bankAccountNo;

    @Size(max = 100)
    @Column(name = "bank_account_name", length = 100)
    private String bankAccountName;

    @Size(max = 100)
    @Column(name = "bank_branch", length = 100)
    private String bankBranch;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @OneToMany(mappedBy = "fundRaiserBank")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignPayment> campaignPayments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("fundRaiserBanks")
    private FundRaiser fundRaiser;

    @ManyToOne
    @JsonIgnoreProperties("fundRaiserBanks")
    private Bank bank;

    @ManyToOne
    @JsonIgnoreProperties("fundRaiserBanks")
    private Currency currency;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public FundRaiserBank bankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public FundRaiserBank bankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
        return this;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public FundRaiserBank bankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return this;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getStatus() {
        return status;
    }

    public FundRaiserBank status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<CampaignPayment> getCampaignPayments() {
        return campaignPayments;
    }

    public FundRaiserBank campaignPayments(Set<CampaignPayment> campaignPayments) {
        this.campaignPayments = campaignPayments;
        return this;
    }

    public FundRaiserBank addCampaignPayment(CampaignPayment campaignPayment) {
        this.campaignPayments.add(campaignPayment);
        campaignPayment.setFundRaiserBank(this);
        return this;
    }

    public FundRaiserBank removeCampaignPayment(CampaignPayment campaignPayment) {
        this.campaignPayments.remove(campaignPayment);
        campaignPayment.setFundRaiserBank(null);
        return this;
    }

    public void setCampaignPayments(Set<CampaignPayment> campaignPayments) {
        this.campaignPayments = campaignPayments;
    }

    public FundRaiser getFundRaiser() {
        return fundRaiser;
    }

    public FundRaiserBank fundRaiser(FundRaiser fundRaiser) {
        this.fundRaiser = fundRaiser;
        return this;
    }

    public void setFundRaiser(FundRaiser fundRaiser) {
        this.fundRaiser = fundRaiser;
    }

    public Bank getBank() {
        return bank;
    }

    public FundRaiserBank bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Currency getCurrency() {
        return currency;
    }

    public FundRaiserBank currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundRaiserBank)) {
            return false;
        }
        return id != null && id.equals(((FundRaiserBank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FundRaiserBank{" +
            "id=" + getId() +
            ", bankAccountNo='" + getBankAccountNo() + "'" +
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankBranch='" + getBankBranch() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
