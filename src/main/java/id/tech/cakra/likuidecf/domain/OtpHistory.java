package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A OtpHistory.
 */
@Entity
@Table(name = "otp_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OtpHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;

    @Column(name = "expired_date")
    private ZonedDateTime expiredDate;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "proc_type")
    private Integer procType;

    @Column(name = "token_text")
    private String tokenText;

    @Column(name = "token")
    private String token;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "retry_max")
    private Integer retryMax;

    @Lob
    @Column(name = "request_data")
    private byte[] requestData;

    @Column(name = "request_data_content_type")
    private String requestDataContentType;

    @ManyToOne
    @JsonIgnoreProperties("otpHistories")
    private MemberAccount memberAccount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public OtpHistory createdBy(Integer createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public OtpHistory createdDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getExpiredDate() {
        return expiredDate;
    }

    public OtpHistory expiredDate(ZonedDateTime expiredDate) {
        this.expiredDate = expiredDate;
        return this;
    }

    public void setExpiredDate(ZonedDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public OtpHistory memberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
        return this;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OtpHistory phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getProcType() {
        return procType;
    }

    public OtpHistory procType(Integer procType) {
        this.procType = procType;
        return this;
    }

    public void setProcType(Integer procType) {
        this.procType = procType;
    }

    public String getTokenText() {
        return tokenText;
    }

    public OtpHistory tokenText(String tokenText) {
        this.tokenText = tokenText;
        return this;
    }

    public void setTokenText(String tokenText) {
        this.tokenText = tokenText;
    }

    public String getToken() {
        return token;
    }

    public OtpHistory token(String token) {
        this.token = token;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public OtpHistory retryCount(Integer retryCount) {
        this.retryCount = retryCount;
        return this;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getRetryMax() {
        return retryMax;
    }

    public OtpHistory retryMax(Integer retryMax) {
        this.retryMax = retryMax;
        return this;
    }

    public void setRetryMax(Integer retryMax) {
        this.retryMax = retryMax;
    }

    public byte[] getRequestData() {
        return requestData;
    }

    public OtpHistory requestData(byte[] requestData) {
        this.requestData = requestData;
        return this;
    }

    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    public String getRequestDataContentType() {
        return requestDataContentType;
    }

    public OtpHistory requestDataContentType(String requestDataContentType) {
        this.requestDataContentType = requestDataContentType;
        return this;
    }

    public void setRequestDataContentType(String requestDataContentType) {
        this.requestDataContentType = requestDataContentType;
    }

    public MemberAccount getMemberAccount() {
        return memberAccount;
    }

    public OtpHistory memberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
        return this;
    }

    public void setMemberAccount(MemberAccount memberAccount) {
        this.memberAccount = memberAccount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OtpHistory)) {
            return false;
        }
        return id != null && id.equals(((OtpHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OtpHistory{" +
            "id=" + getId() +
            ", createdBy=" + getCreatedBy() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", expiredDate='" + getExpiredDate() + "'" +
            ", memberEmail='" + getMemberEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", procType=" + getProcType() +
            ", tokenText='" + getTokenText() + "'" +
            ", token='" + getToken() + "'" +
            ", retryCount=" + getRetryCount() +
            ", retryMax=" + getRetryMax() +
            ", requestData='" + getRequestData() + "'" +
            ", requestDataContentType='" + getRequestDataContentType() + "'" +
            "}";
    }
}
