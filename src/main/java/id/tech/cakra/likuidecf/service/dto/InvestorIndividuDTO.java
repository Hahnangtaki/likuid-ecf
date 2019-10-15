package id.tech.cakra.likuidecf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.InvestorIndividu} entity.
 */
public class InvestorIndividuDTO implements Serializable {

    private Long id;

    @Size(max = 15)
    private String sid;

    @Size(max = 35)
    private String firstName;

    @Size(max = 35)
    private String middleName;

    @Size(max = 35)
    private String lastName;

    private Long nationalityId;

    @Size(max = 25)
    private String ktp;

    private LocalDate ktpExpDate;

    @Size(max = 15)
    private String npwp;

    private LocalDate npwpRegDate;

    @Size(max = 25)
    private String passport;

    private LocalDate passportExpDate;

    @Size(max = 30)
    private String kitas;

    private LocalDate kitasExpDate;

    @Size(max = 100)
    private String birthPlace;

    private LocalDate birthDate;

    @Size(max = 1)
    private String sex;

    @Size(max = 1)
    private String maritalStatus;

    @Size(max = 100)
    private String spouseName;

    @Size(max = 120)
    private String heir;

    @Size(max = 120)
    private String heirRalation;

    @Size(max = 1)
    private String educationBackground;

    @Size(max = 1)
    private String occupation;

    @Size(max = 120)
    private String natureOfBusiness;

    private Double annualIncome;

    @Size(max = 30)
    private String fundSource;

    @Size(max = 120)
    private String fundSourceText;

    @Size(max = 100)
    private String description;

    @Size(max = 10)
    private String investmentObjective;

    @Size(max = 100)
    private String motherMaidenName;

    @Size(max = 15)
    private String directSid;

    @Size(max = 1)
    private String assetOwner;

    private LocalDate authPersonKtpRegDate;


    private Long taxId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public LocalDate getKtpExpDate() {
        return ktpExpDate;
    }

    public void setKtpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getPassportExpDate() {
        return passportExpDate;
    }

    public void setPassportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
    }

    public String getKitas() {
        return kitas;
    }

    public void setKitas(String kitas) {
        this.kitas = kitas;
    }

    public LocalDate getKitasExpDate() {
        return kitasExpDate;
    }

    public void setKitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getHeir() {
        return heir;
    }

    public void setHeir(String heir) {
        this.heir = heir;
    }

    public String getHeirRalation() {
        return heirRalation;
    }

    public void setHeirRalation(String heirRalation) {
        this.heirRalation = heirRalation;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public Double getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getFundSource() {
        return fundSource;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getFundSourceText() {
        return fundSourceText;
    }

    public void setFundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getMotherMaidenName() {
        return motherMaidenName;
    }

    public void setMotherMaidenName(String motherMaidenName) {
        this.motherMaidenName = motherMaidenName;
    }

    public String getDirectSid() {
        return directSid;
    }

    public void setDirectSid(String directSid) {
        this.directSid = directSid;
    }

    public String getAssetOwner() {
        return assetOwner;
    }

    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    public LocalDate getAuthPersonKtpRegDate() {
        return authPersonKtpRegDate;
    }

    public void setAuthPersonKtpRegDate(LocalDate authPersonKtpRegDate) {
        this.authPersonKtpRegDate = authPersonKtpRegDate;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestorIndividuDTO investorIndividuDTO = (InvestorIndividuDTO) o;
        if (investorIndividuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investorIndividuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestorIndividuDTO{" +
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
            ", tax=" + getTaxId() +
            "}";
    }
}
