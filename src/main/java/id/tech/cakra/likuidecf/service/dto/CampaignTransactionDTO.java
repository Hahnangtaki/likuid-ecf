package id.tech.cakra.likuidecf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.CampaignTransaction} entity.
 */
public class CampaignTransactionDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String transCode;

    @Size(max = 200)
    private String transDesc;

    private LocalDate transDate;

    @Size(max = 1)
    private String transType;

    private Long qty;

    private Double amount;

    private Double uniqueCharges;

    private Double netAmount;

    @Size(max = 1)
    private String status;


    private Long investorId;

    private Long campaignId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUniqueCharges() {
        return uniqueCharges;
    }

    public void setUniqueCharges(Double uniqueCharges) {
        this.uniqueCharges = uniqueCharges;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CampaignTransactionDTO campaignTransactionDTO = (CampaignTransactionDTO) o;
        if (campaignTransactionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignTransactionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignTransactionDTO{" +
            "id=" + getId() +
            ", transCode='" + getTransCode() + "'" +
            ", transDesc='" + getTransDesc() + "'" +
            ", transDate='" + getTransDate() + "'" +
            ", transType='" + getTransType() + "'" +
            ", qty=" + getQty() +
            ", amount=" + getAmount() +
            ", uniqueCharges=" + getUniqueCharges() +
            ", netAmount=" + getNetAmount() +
            ", status='" + getStatus() + "'" +
            ", investor=" + getInvestorId() +
            ", campaign=" + getCampaignId() +
            "}";
    }
}
