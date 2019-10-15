package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A InvestorIndividu.
 */
@Entity
@Table(name = "investor_individu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvestorIndividu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 15)
    @Column(name = "sid", length = 15)
    private String sid;

    @Size(max = 35)
    @Column(name = "first_name", length = 35)
    private String firstName;

    @Size(max = 35)
    @Column(name = "middle_name", length = 35)
    private String middleName;

    @Size(max = 35)
    @Column(name = "last_name", length = 35)
    private String lastName;

    @Column(name = "nationality_id")
    private Long nationalityId;

    @Size(max = 25)
    @Column(name = "ktp", length = 25)
    private String ktp;

    @Column(name = "ktp_exp_date")
    private LocalDate ktpExpDate;

    @Size(max = 15)
    @Column(name = "npwp", length = 15)
    private String npwp;

    @Column(name = "npwp_reg_date")
    private LocalDate npwpRegDate;

    @Size(max = 25)
    @Column(name = "passport", length = 25)
    private String passport;

    @Column(name = "passport_exp_date")
    private LocalDate passportExpDate;

    @Size(max = 30)
    @Column(name = "kitas", length = 30)
    private String kitas;

    @Column(name = "kitas_exp_date")
    private LocalDate kitasExpDate;

    @Size(max = 100)
    @Column(name = "birth_place", length = 100)
    private String birthPlace;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Size(max = 1)
    @Column(name = "sex", length = 1)
    private String sex;

    @Size(max = 1)
    @Column(name = "marital_status", length = 1)
    private String maritalStatus;

    @Size(max = 100)
    @Column(name = "spouse_name", length = 100)
    private String spouseName;

    @Size(max = 120)
    @Column(name = "heir", length = 120)
    private String heir;

    @Size(max = 120)
    @Column(name = "heir_ralation", length = 120)
    private String heirRalation;

    @Size(max = 1)
    @Column(name = "education_background", length = 1)
    private String educationBackground;

    @Size(max = 1)
    @Column(name = "occupation", length = 1)
    private String occupation;

    @Size(max = 120)
    @Column(name = "nature_of_business", length = 120)
    private String natureOfBusiness;

    @Column(name = "annual_income")
    private Double annualIncome;

    @Size(max = 30)
    @Column(name = "fund_source", length = 30)
    private String fundSource;

    @Size(max = 120)
    @Column(name = "fund_source_text", length = 120)
    private String fundSourceText;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 10)
    @Column(name = "investment_objective", length = 10)
    private String investmentObjective;

    @Size(max = 100)
    @Column(name = "mother_maiden_name", length = 100)
    private String motherMaidenName;

    @Size(max = 15)
    @Column(name = "direct_sid", length = 15)
    private String directSid;

    @Size(max = 1)
    @Column(name = "asset_owner", length = 1)
    private String assetOwner;

    @Column(name = "auth_person_ktp_reg_date")
    private LocalDate authPersonKtpRegDate;

    @OneToOne(mappedBy = "investorIndividu")
    @JsonIgnore
    private Investor investor;

    @ManyToOne
    @JsonIgnoreProperties("investorIndividus")
    private Tax tax;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public InvestorIndividu sid(String sid) {
        this.sid = sid;
        return this;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public InvestorIndividu firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public InvestorIndividu middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public InvestorIndividu lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public InvestorIndividu nationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
        return this;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getKtp() {
        return ktp;
    }

    public InvestorIndividu ktp(String ktp) {
        this.ktp = ktp;
        return this;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public LocalDate getKtpExpDate() {
        return ktpExpDate;
    }

    public InvestorIndividu ktpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
        return this;
    }

    public void setKtpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
    }

    public String getNpwp() {
        return npwp;
    }

    public InvestorIndividu npwp(String npwp) {
        this.npwp = npwp;
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public InvestorIndividu npwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
        return this;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getPassport() {
        return passport;
    }

    public InvestorIndividu passport(String passport) {
        this.passport = passport;
        return this;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getPassportExpDate() {
        return passportExpDate;
    }

    public InvestorIndividu passportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
        return this;
    }

    public void setPassportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
    }

    public String getKitas() {
        return kitas;
    }

    public InvestorIndividu kitas(String kitas) {
        this.kitas = kitas;
        return this;
    }

    public void setKitas(String kitas) {
        this.kitas = kitas;
    }

    public LocalDate getKitasExpDate() {
        return kitasExpDate;
    }

    public InvestorIndividu kitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
        return this;
    }

    public void setKitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public InvestorIndividu birthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
        return this;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public InvestorIndividu birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public InvestorIndividu sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public InvestorIndividu maritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public InvestorIndividu spouseName(String spouseName) {
        this.spouseName = spouseName;
        return this;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getHeir() {
        return heir;
    }

    public InvestorIndividu heir(String heir) {
        this.heir = heir;
        return this;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public String getHeirRalation() {
        return heirRalation;
    }

    public InvestorIndividu heirRalation(String heirRalation) {
        this.heirRalation = heirRalation;
        return this;
    }

    public void setHeirRalation(String heirRalation) {
        this.heirRalation = heirRalation;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public InvestorIndividu educationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
        return this;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getOccupation() {
        return occupation;
    }

    public InvestorIndividu occupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public InvestorIndividu natureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
        return this;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public InvestorIndividu annualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
        return this;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getFundSource() {
        return fundSource;
    }

    public InvestorIndividu fundSource(String fundSource) {
        this.fundSource = fundSource;
        return this;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getFundSourceText() {
        return fundSourceText;
    }

    public InvestorIndividu fundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
        return this;
    }

    public void setFundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
    }

    public String getDescription() {
        return description;
    }

    public InvestorIndividu description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public InvestorIndividu investmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
        return this;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public InvestorIndividu motherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
        return this;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getDirectSid() {
        return directSid;
    }

    public InvestorIndividu directSid(String directSid) {
        this.directSid = directSid;
        return this;
    }

    public void setDirectSid(String directSid) {
        this.directSid = directSid;
    }

    public String getAssetOwner() {
        return assetOwner;
    }

    public InvestorIndividu assetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
        return this;
    }

    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    public LocalDate getAuthPersonKtpRegDate() {
        return authPersonKtpRegDate;
    }

    public InvestorIndividu authPersonKtpRegDate(LocalDate authPersonKtpRegDate) {
        this.authPersonKtpRegDate = authPersonKtpRegDate;
        return this;
    }

    public void setAuthPersonKtpRegDate(LocalDate authPersonKtpRegDate) {
        this.authPersonKtpRegDate = authPersonKtpRegDate;
    }

    public Investor getInvestor() {
        return investor;
    }

    public InvestorIndividu investor(Investor investor) {
        this.investor = investor;
        return this;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Tax getTax() {
        return tax;
    }

    public InvestorIndividu tax(Tax tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvestorIndividu)) {
            return false;
        }
        return id != null && id.equals(((InvestorIndividu) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvestorIndividu{" +
            "id=" + getId() +
            ", sid='" + getSid() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", nationalityId=" + getNationalityId() +
            ", ktp='" + getKtp() + "'" +
            ", ktpExpDate='" + getKtpExpDate() + "'" +
            ", npwp='" + getNpwp() + "'" +
            ", npwpRegDate='" + getNpwpRegDate() + "'" +
            ", passport='" + getPassport() + "'" +
            ", passportExpDate='" + getPassportExpDate() + "'" +
            ", kitas='" + getKitas() + "'" +
            ", kitasExpDate='" + getKitasExpDate() + "'" +
            ", birthPlace='" + getBirthPlace() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", sex='" + getSex() + "'" +
            ", maritalStatus='" + getMaritalStatus() + "'" +
            ", spouseName='" + getSpouseName() + "'" +
            ", heir='" + getHeir() + "'" +
            ", heirRalation='" + getHeirRalation() + "'" +
            ", educationBackground='" + getEducationBackground() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", natureOfBusiness='" + getNatureOfBusiness() + "'" +
            ", annualIncome=" + getAnnualIncome() +
            ", fundSource='" + getFundSource() + "'" +
            ", fundSourceText='" + getFundSourceText() + "'" +
            ", description='" + getDescription() + "'" +
            ", investmentObjective='" + getInvestmentObjective() + "'" +
            ", motherMaidenName='" + getMotherMaidenName() + "'" +
            ", directSid='" + getDirectSid() + "'" +
            ", assetOwner='" + getAssetOwner() + "'" +
            ", authPersonKtpRegDate='" + getAuthPersonKtpRegDate() + "'" +
            "}";
    }
}
