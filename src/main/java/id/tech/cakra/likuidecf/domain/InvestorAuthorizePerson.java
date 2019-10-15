package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A InvestorAuthorizePerson.
 */
@Entity
@Table(name = "investor_authorize_person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvestorAuthorizePerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 35)
    @Column(name = "first_name", length = 35)
    private String firstName;

    @Size(max = 35)
    @Column(name = "middle_name", length = 35)
    private String middleName;

    @Size(max = 35)
    @Column(name = "last_name", length = 35)
    private String lastName;

    @Size(max = 100)
    @Column(name = "position", length = 100)
    private String position;

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

    @ManyToOne
    @JsonIgnoreProperties("investorAuthorizePeople")
    private Investor investor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public InvestorAuthorizePerson firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public InvestorAuthorizePerson middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public InvestorAuthorizePerson lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public InvestorAuthorizePerson position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getKtp() {
        return ktp;
    }

    public InvestorAuthorizePerson ktp(String ktp) {
        this.ktp = ktp;
        return this;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public LocalDate getKtpExpDate() {
        return ktpExpDate;
    }

    public InvestorAuthorizePerson ktpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
        return this;
    }

    public void setKtpExpDate(LocalDate ktpExpDate) {
        this.ktpExpDate = ktpExpDate;
    }

    public String getNpwp() {
        return npwp;
    }

    public InvestorAuthorizePerson npwp(String npwp) {
        this.npwp = npwp;
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public InvestorAuthorizePerson npwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
        return this;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getPassport() {
        return passport;
    }

    public InvestorAuthorizePerson passport(String passport) {
        this.passport = passport;
        return this;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getPassportExpDate() {
        return passportExpDate;
    }

    public InvestorAuthorizePerson passportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
        return this;
    }

    public void setPassportExpDate(LocalDate passportExpDate) {
        this.passportExpDate = passportExpDate;
    }

    public String getKitas() {
        return kitas;
    }

    public InvestorAuthorizePerson kitas(String kitas) {
        this.kitas = kitas;
        return this;
    }

    public void setKitas(String kitas) {
        this.kitas = kitas;
    }

    public LocalDate getKitasExpDate() {
        return kitasExpDate;
    }

    public InvestorAuthorizePerson kitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
        return this;
    }

    public void setKitasExpDate(LocalDate kitasExpDate) {
        this.kitasExpDate = kitasExpDate;
    }

    public Investor getInvestor() {
        return investor;
    }

    public InvestorAuthorizePerson investor(Investor investor) {
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
        if (!(o instanceof InvestorAuthorizePerson)) {
            return false;
        }
        return id != null && id.equals(((InvestorAuthorizePerson) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvestorAuthorizePerson{" +
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
            "}";
    }
}
