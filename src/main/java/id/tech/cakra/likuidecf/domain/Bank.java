package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Bank.
 */
@Entity
@Table(name = "bank")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "bank_code", length = 10)
    private String bankCode;

    @Size(max = 60)
    @Column(name = "bank_name", length = 60)
    private String bankName;

    @Size(max = 60)
    @Column(name = "initial_name", length = 60)
    private String initialName;

    @Size(max = 3)
    @Column(name = "bi_code", length = 3)
    private String biCode;

    @Size(max = 20)
    @Column(name = "swift_code", length = 20)
    private String swiftCode;

    @OneToMany(mappedBy = "bank")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FundRaiserBank> fundRaiserBanks = new HashSet<>();

    @OneToMany(mappedBy = "bank")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CompanyBank> companyBanks = new HashSet<>();

    @OneToMany(mappedBy = "bank")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorBank> investorBanks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public Bank bankCode(String bankCode) {
        this.bankCode = bankCode;
        return this;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public Bank bankName(String bankName) {
        this.bankName = bankName;
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getInitialName() {
        return initialName;
    }

    public Bank initialName(String initialName) {
        this.initialName = initialName;
        return this;
    }

    public void setInitialName(String initialName) {
        this.initialName = initialName;
    }

    public String getBiCode() {
        return biCode;
    }

    public Bank biCode(String biCode) {
        this.biCode = biCode;
        return this;
    }

    public void setBiCode(String biCode) {
        this.biCode = biCode;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public Bank swiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
        return this;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public Set<FundRaiserBank> getFundRaiserBanks() {
        return fundRaiserBanks;
    }

    public Bank fundRaiserBanks(Set<FundRaiserBank> fundRaiserBanks) {
        this.fundRaiserBanks = fundRaiserBanks;
        return this;
    }

    public Bank addFundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBanks.add(fundRaiserBank);
        fundRaiserBank.setBank(this);
        return this;
    }

    public Bank removeFundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBanks.remove(fundRaiserBank);
        fundRaiserBank.setBank(null);
        return this;
    }

    public void setFundRaiserBanks(Set<FundRaiserBank> fundRaiserBanks) {
        this.fundRaiserBanks = fundRaiserBanks;
    }

    public Set<CompanyBank> getCompanyBanks() {
        return companyBanks;
    }

    public Bank companyBanks(Set<CompanyBank> companyBanks) {
        this.companyBanks = companyBanks;
        return this;
    }

    public Bank addCompanyBank(CompanyBank companyBank) {
        this.companyBanks.add(companyBank);
        companyBank.setBank(this);
        return this;
    }

    public Bank removeCompanyBank(CompanyBank companyBank) {
        this.companyBanks.remove(companyBank);
        companyBank.setBank(null);
        return this;
    }

    public void setCompanyBanks(Set<CompanyBank> companyBanks) {
        this.companyBanks = companyBanks;
    }

    public Set<InvestorBank> getInvestorBanks() {
        return investorBanks;
    }

    public Bank investorBanks(Set<InvestorBank> investorBanks) {
        this.investorBanks = investorBanks;
        return this;
    }

    public Bank addInvestorBank(InvestorBank investorBank) {
        this.investorBanks.add(investorBank);
        investorBank.setBank(this);
        return this;
    }

    public Bank removeInvestorBank(InvestorBank investorBank) {
        this.investorBanks.remove(investorBank);
        investorBank.setBank(null);
        return this;
    }

    public void setInvestorBanks(Set<InvestorBank> investorBanks) {
        this.investorBanks = investorBanks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        return id != null && id.equals(((Bank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Bank{" +
            "id=" + getId() +
            ", bankCode='" + getBankCode() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", initialName='" + getInitialName() + "'" +
            ", biCode='" + getBiCode() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            "}";
    }
}
