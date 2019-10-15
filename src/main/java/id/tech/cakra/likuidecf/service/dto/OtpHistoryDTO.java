package id.tech.cakra.likuidecf.service.dto;
import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.OtpHistory} entity.
 */
public class OtpHistoryDTO implements Serializable {

    private Long id;

    private Integer createdBy;

    private ZonedDateTime createdDate;

    private ZonedDateTime expiredDate;

    private String memberEmail;

    private String phoneNumber;

    private Integer procType;

    private String tokenText;

    private String token;

    private Integer retryCount;

    private Integer retryMax;

    @Lob
    private byte[] requestData;

    private String requestDataContentType;

    private Long memberAccountId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(ZonedDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getProcType() {
        return procType;
    }

    public void setProcType(Integer procType) {
        this.procType = procType;
    }

    public String getTokenText() {
        return tokenText;
    }

    public void setTokenText(String tokenText) {
        this.tokenText = tokenText;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Integer getRetryMax() {
        return retryMax;
    }

    public void setRetryMax(Integer retryMax) {
        this.retryMax = retryMax;
    }

    public byte[] getRequestData() {
        return requestData;
    }

    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    public String getRequestDataContentType() {
        return requestDataContentType;
    }

    public void setRequestDataContentType(String requestDataContentType) {
        this.requestDataContentType = requestDataContentType;
    }

    public Long getMemberAccountId() {
        return memberAccountId;
    }

    public void setMemberAccountId(Long memberAccountId) {
        this.memberAccountId = memberAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OtpHistoryDTO otpHistoryDTO = (OtpHistoryDTO) o;
        if (otpHistoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), otpHistoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OtpHistoryDTO{" +
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
            ", memberAccount=" + getMemberAccountId() +
            "}";
    }
}
