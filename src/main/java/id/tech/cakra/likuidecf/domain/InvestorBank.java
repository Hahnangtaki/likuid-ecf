package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A InvestorBank.
 */
@Entity
@Table(name = "investor_bank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvestorBank implements Serializable {

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

    @ManyToOne
    @JsonIgnoreProperties("investorBanks")
    private Bank bank;

    @ManyToOne
    @JsonIgnoreProperties("investorBanks")
    private Currency currency;

    @ManyToOne
    @JsonIgnoreProperties("investorBanks")
    private Investor investor;

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

    public InvestorBank bankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
        return this;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public InvestorBank bankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
        return this;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public InvestorBank bankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return this;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getStatus() {
        return status;
    }

    public InvestorBank status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bank getBank() {
        return bank;
    }

    public InvestorBank bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Currency getCurrency() {
        return currency;
    }

    public InvestorBank currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Investor getInvestor() {
        return investor;
    }

    public InvestorBank investor(Investor investor) {
        this.investor = investor;
        return this;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvestorBank)) {
            return false;
        }
        return id != null && id.equals(((InvestorBank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvestorBank{" +
            "id=" + getId() +
            ", bankAccountNo='" + getBankAccountNo() + "'" +
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankBranch='" + getBankBranch() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
