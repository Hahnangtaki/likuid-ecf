package id.tech.cakra.likuidecf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.Campaign} entity.
 */
public class CampaignDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String campaignCode;

    @Size(max = 100)
    private String campaignName;

    @Size(max = 100)
    private String campaignDesc;

    @Size(max = 10)
    private String investmentType;

    private LocalDate offerDate;

    private LocalDate deadlineDate;

    private Double price;

    private Double fundTarget;

    private Double fundRaised;

    private Double fundPaid;

    private Long qtyTarget;

    private Long qtyRaised;

    private Double estRoiMin;

    private Double estRoiMax;

    @Lob
    private byte[] prospectusFile;

    private String prospectusFileContentType;
    private String status;


    private Long currencyId;

    private Long fundRaiserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignDesc() {
        return campaignDesc;
    }

    public void setCampaignDesc(String campaignDesc) {
        this.campaignDesc = campaignDesc;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFundTarget() {
        return fundTarget;
    }

    public void setFundTarget(Double fundTarget) {
        this.fundTarget = fundTarget;
    }

    public Double getFundRaised() {
        return fundRaised;
    }

    public void setFundRaised(Double fundRaised) {
        this.fundRaised = fundRaised;
    }

    public Double getFundPaid() {
        return fundPaid;
    }

    public void setFundPaid(Double fundPaid) {
        this.fundPaid = fundPaid;
    }

    public Long getQtyTarget() {
        return qtyTarget;
    }

    public void setQtyTarget(Long qtyTarget) {
        this.qtyTarget = qtyTarget;
    }

    public Long getQtyRaised() {
        return qtyRaised;
    }

    public void setQtyRaised(Long qtyRaised) {
        this.qtyRaised = qtyRaised;
    }

    public Double getEstRoiMin() {
        return estRoiMin;
    }

    public void setEstRoiMin(Double estRoiMin) {
        this.estRoiMin = estRoiMin;
    }

    public Double getEstRoiMax() {
        return estRoiMax;
    }

    public void setEstRoiMax(Double estRoiMax) {
        this.estRoiMax = estRoiMax;
    }

    public byte[] getProspectusFile() {
        return prospectusFile;
    }

    public void setProspectusFile(byte[] prospectusFile) {
        this.prospectusFile = prospectusFile;
    }

    public String getProspectusFileContentType() {
        return prospectusFileContentType;
    }

    public void setProspectusFileContentType(String prospectusFileContentType) {
        this.prospectusFileContentType = prospectusFileContentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getFundRaiserId() {
        return fundRaiserId;
    }

    public void setFundRaiserId(Long fundRaiserId) {
        this.fundRaiserId = fundRaiserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CampaignDTO campaignDTO = (CampaignDTO) o;
        if (campaignDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campaignDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampaignDTO{" +
            "id=" + getId() +
            ", campaignCode='" + getCampaignCode() + "'" +
            ", campaignName='" + getCampaignName() + "'" +
            ", campaignDesc='" + getCampaignDesc() + "'" +
            ", investmentType='" + getInvestmentType() + "'" +
            ", offerDate='" + getOfferDate() + "'" +
            ", deadlineDate='" + getDeadlineDate() + "'" +
            ", price=" + getPrice() +
            ", fundTarget=" + getFundTarget() +
            ", fundRaised=" + getFundRaised() +
            ", fundPaid=" + getFundPaid() +
            ", qtyTarget=" + getQtyTarget() +
            ", qtyRaised=" + getQtyRaised() +
            ", estRoiMin=" + getEstRoiMin() +
            ", estRoiMax=" + getEstRoiMax() +
            ", prospectusFile='" + getProspectusFile() + "'" +
            ", status='" + getStatus() + "'" +
            ", currency=" + getCurrencyId() +
            ", fundRaiser=" + getFundRaiserId() +
            "}";
    }
}
