package id.tech.cakra.likuidecf.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.CampaignInvestor} entity.
 */
public class CampaignInvestorDTO implements Serializable {

    private Long id;

    private Long qty;

    private Double fundAmount;


    private Long investorId;

    private Long campaignId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getFundAmount() {
        return fundAmount;
    }

    public void setFundAmount(Double fundAmount) {
        this.fundAmount = fundAmount;
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

        CampaignInvestorDTO campaignInvestorDTO = (CampaignInvestorDTO) o;
        if (campaignInvestorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignInvestorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignInvestorDTO{" +
            "id=" + getId() +
            ", qty=" + getQty() +
            ", fundAmount=" + getFundAmount() +
            ", investor=" + getInvestorId() +
            ", campaign=" + getCampaignId() +
            "}";
    }
}
