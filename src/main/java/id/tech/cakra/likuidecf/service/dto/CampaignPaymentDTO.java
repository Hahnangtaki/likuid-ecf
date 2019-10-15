package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.CampaignPayment} entity.
 */
public class CampaignPaymentDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String paymentCode;

    @Size(max = 200)
    private String paymentDesc;

    private Double amount;

    @Size(max = 1)
    private String status;


    private Long fundRaiserBankId;

    private Long campaignId;

    private Long companyBankId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentDesc() {
        return paymentDesc;
    }

    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getFundRaiserBankId() {
        return fundRaiserBankId;
    }

    public void setFundRaiserBankId(Long fundRaiserBankId) {
        this.fundRaiserBankId = fundRaiserBankId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getCompanyBankId() {
        return companyBankId;
    }

    public void setCompanyBankId(Long companyBankId) {
        this.companyBankId = companyBankId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CampaignPaymentDTO campaignPaymentDTO = (CampaignPaymentDTO) o;
        if (campaignPaymentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignPaymentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignPaymentDTO{" +
            "id=" + getId() +
            ", paymentCode='" + getPaymentCode() + "'" +
            ", paymentDesc='" + getPaymentDesc() + "'" +
            ", amount=" + getAmount() +
            ", status='" + getStatus() + "'" +
            ", fundRaiserBank=" + getFundRaiserBankId() +
            ", campaign=" + getCampaignId() +
            ", companyBank=" + getCompanyBankId() +
            "}";
    }
}
