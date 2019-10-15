package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A MemberAccount.
 */
@Entity
@Table(name = "member_account")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MemberAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "member_since")
    private LocalDate memberSince;

    @Column(name = "member_status")
    private Integer memberStatus;

    @OneToMany(mappedBy = "memberAccount")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OtpHistory> otpHistories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public MemberAccount memberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
        return this;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public MemberAccount phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getMemberSince() {
        return memberSince;
    }

    public MemberAccount memberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
        return this;
    }

    public void setMemberSince(LocalDate memberSince) {
        this.memberSince = memberSince;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public MemberAccount memberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
        return this;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Set<OtpHistory> getOtpHistories() {
        return otpHistories;
    }

    public MemberAccount otpHistories(Set<OtpHistory> otpHistories) {
        this.otpHistories = otpHistories;
        return this;
    }

    public MemberAccount addOtpHistory(OtpHistory otpHistory) {
        this.otpHistories.add(otpHistory);
        otpHistory.setMemberAccount(this);
        return this;
    }

    public MemberAccount removeOtpHistory(OtpHistory otpHistory) {
        this.otpHistories.remove(otpHistory);
        otpHistory.setMemberAccount(null);
        return this;
    }

    public void setOtpHistories(Set<OtpHistory> otpHistories) {
        this.otpHistories = otpHistories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MemberAccount)) {
            return false;
        }
        return id != null && id.equals(((MemberAccount) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MemberAccount{" +
            "id=" + getId() +
            ", memberEmail='" + getMemberEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", memberSince='" + getMemberSince() + "'" +
            ", memberStatus=" + getMemberStatus() +
            "}";
    }
}
