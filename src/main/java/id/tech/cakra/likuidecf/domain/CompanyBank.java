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
 * A CompanyBank.
 */
@Entity
@Table(name = "company_bank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CompanyBank implements Serializable {

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

    @OneToMany(mappedBy = "companyBank")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignPayment> campaignPayments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("companyBanks")
    private Bank bank;

    @ManyToOne
    @JsonIgnoreProperties("companyBanks")
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

    public CompanyBank bankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public CompanyBank bankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
        return this;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public CompanyBank bankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return this;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getStatus() {
        return status;
    }

    public CompanyBank status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<CampaignPayment> getCampaignPayments() {
        return campaignPayments;
    }

    public CompanyBank campaignPayments(Set<CampaignPayment> campaignPayments) {
        this.campaignPayments = campaignPayments;
        return this;
    }

    public CompanyBank addCampaignPayment(CampaignPayment campaignPayment) {
        this.campaignPayments.add(campaignPayment);
        campaignPayment.setCompanyBank(this);
        return this;
    }

    public CompanyBank removeCampaignPayment(CampaignPayment campaignPayment) {
        this.campaignPayments.remove(campaignPayment);
        campaignPayment.setCompanyBank(null);
        return this;
    }

    public void setCampaignPayments(Set<CampaignPayment> campaignPayments) {
        this.campaignPayments = campaignPayments;
    }

    public Bank getBank() {
        return bank;
    }

    public CompanyBank bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Currency getCurrency() {
        return currency;
    }

    public CompanyBank currency(Currency currency) {
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
        if (!(o instanceof CompanyBank)) {
            return false;
        }
        return id != null && id.equals(((CompanyBank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CompanyBank{" +
            "id=" + getId() +
            ", bankAccountNo='" + getBankAccountNo() + "'" +
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankBranch='" + getBankBranch() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
