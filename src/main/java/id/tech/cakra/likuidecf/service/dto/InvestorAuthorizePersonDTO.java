package id.tech.cakra.likuidecf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.InvestorAuthorizePerson} entity.
 */
public class InvestorAuthorizePersonDTO implements Serializable {

    private Long id;

    @Size(max = 35)
    private String firstName;

    @Size(max = 35)
    private String middleName;

    @Size(max = 35)
    private String lastName;

    @Size(max = 100)
    private String position;

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


    private Long investorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestorAuthorizePersonDTO investorAuthorizePersonDTO = (InvestorAuthorizePersonDTO) o;
        if (investorAuthorizePersonDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investorAuthorizePersonDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestorAuthorizePersonDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", position='" + getPosition() + "'" +
            ", ktp='" + getKtp() + "'" +
            ", ktpExpDate='" + getKtpExpDate() + "'" +
            ", npwp='" + getNpwp() + "'" +
            ", npwpRegDate='" + getNpwpRegDate() + "'" +
            ", passport='" + getPassport() + "'" +
            ", passportExpDate='" + getPassportExpDate() + "'" +
            ", kitas='" + getKitas() + "'" +
            ", kitasExpDate='" + getKitasExpDate() + "'" +
            ", investor=" + getInvestorId() +
            "}";
    }
}
