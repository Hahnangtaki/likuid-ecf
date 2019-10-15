package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Campaign.
 */
@Entity
@Table(name = "campaign")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "campaign_code", length = 10)
    private String campaignCode;

    @Size(max = 100)
    @Column(name = "campaign_name", length = 100)
    private String campaignName;

    @Size(max = 100)
    @Column(name = "campaign_desc", length = 100)
    private String campaignDesc;

    @Size(max = 10)
    @Column(name = "investment_type", length = 10)
    private String investmentType;

    @Column(name = "offer_date")
    private LocalDate offerDate;

    @Column(name = "deadline_date")
    private LocalDate deadlineDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "fund_target")
    private Double fundTarget;

    @Column(name = "fund_raised")
    private Double fundRaised;

    @Column(name = "fund_paid")
    private Double fundPaid;

    @Column(name = "qty_target")
    private Long qtyTarget;

    @Column(name = "qty_raised")
    private Long qtyRaised;

    @Column(name = "est_roi_min")
    private Double estRoiMin;

    @Column(name = "est_roi_max")
    private Double estRoiMax;

    @Lob
    @Column(name = "prospectus_file")
    private byte[] prospectusFile;

    @Column(name = "prospectus_file_content_type")
    private String prospectusFileContentType;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "campaign")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignPayment> campaignPayments = new HashSet<>();

    @OneToMany(mappedBy = "campaign")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignInvestor> campaignInvestors = new HashSet<>();

    @OneToMany(mappedBy = "campaign")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignTransaction> campaignTransactions = new HashSet<>();

    @OneToMany(mappedBy = "campaign")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignCategory> campaignCategories = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("campaigns")
    private Currency currency;

    @ManyToOne
    @JsonIgnoreProperties("campaigns")
    private FundRaiser fundRaiser;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampaignCode() {
        return campaignCode;
    }

    public Campaign campaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
        return this;
    }

    public void setCampaignCode(String campaignCode) {
        this.campaignCode = campaignCode;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public Campaign campaignName(String campaignName) {
        this.campaignName = campaignName;
        return this;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignDesc() {
        return campaignDesc;
    }

    public Campaign campaignDesc(String campaignDesc) {
        this.campaignDesc = campaignDesc;
        return this;
    }

    public void setCampaignDesc(String campaignDesc) {
        this.campaignDesc = campaignDesc;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public Campaign investmentType(String investmentType) {
        this.investmentType = investmentType;
        return this;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public Campaign offerDate(LocalDate offerDate) {
        this.offerDate = offerDate;
        return this;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public Campaign deadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
        return this;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public Double getPrice() {
        return price;
    }

    public Campaign price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getFundTarget() {
        return fundTarget;
    }

    public Campaign fundTarget(Double fundTarget) {
        this.fundTarget = fundTarget;
        return this;
    }

    public void setFundTarget(Double fundTarget) {
        this.fundTarget = fundTarget;
    }

    public Double getFundRaised() {
        return fundRaised;
    }

    public Campaign fundRaised(Double fundRaised) {
        this.fundRaised = fundRaised;
        return this;
    }

    public void setFundRaised(Double fundRaised) {
        this.fundRaised = fundRaised;
    }

    public Double getFundPaid() {
        return fundPaid;
    }

    public Campaign fundPaid(Double fundPaid) {
        this.fundPaid = fundPaid;
        return this;
    }

    public void setFundPaid(Double fundPaid) {
        this.fundPaid = fundPaid;
    }

    public Long getQtyTarget() {
        return qtyTarget;
    }

    public Campaign qtyTarget(Long qtyTarget) {
        this.qtyTarget = qtyTarget;
        return this;
    }

    public void setQtyTarget(Long qtyTarget) {
        this.qtyTarget = qtyTarget;
    }

    public Long getQtyRaised() {
        return qtyRaised;
    }

    public Campaign qtyRaised(Long qtyRaised) {
        this.qtyRaised = qtyRaised;
        return this;
    }

    public void setQtyRaised(Long qtyRaised) {
        this.qtyRaised = qtyRaised;
    }

    public Double getEstRoiMin() {
        return estRoiMin;
    }

    public Campaign estRoiMin(Double estRoiMin) {
        this.estRoiMin = estRoiMin;
        return this;
    }

    public void setEstRoiMin(Double estRoiMin) {
        this.estRoiMin = estRoiMin;
    }

    public Double getEstRoiMax() {
        return estRoiMax;
    }

    public Campaign estRoiMax(Double estRoiMax) {
        this.estRoiMax = estRoiMax;
        return this;
    }

    public void setEstRoiMax(Double estRoiMax) {
        this.estRoiMax = estRoiMax;
    }

    public byte[] getProspectusFile() {
        return prospectusFile;
    }

    public Campaign prospectusFile(byte[] prospectusFile) {
        this.prospectusFile = prospectusFile;
        return this;
    }

    public void setProspectusFile(byte[] prospectusFile) {
        this.prospectusFile = prospectusFile;
    }

    public String getProspectusFileContentType() {
        return prospectusFileContentType;
    }

    public Campaign prospectusFileContentType(String prospectusFileContentType) {
        this.prospectusFileContentType = prospectusFileContentType;
        return this;
    }

    public void setProspectusFileContentType(String prospectusFileContentType) {
        this.prospectusFileContentType = prospectusFileContentType;
    }

    public String getStatus() {
        return status;
    }

    public Campaign status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<CampaignPayment> getCampaignPayments() {
        return campaignPayments;
    }

    public Campaign campaignPayments(Set<CampaignPayment> campaignPayments) {
        this.campaignPayments = campaignPayments;
        return this;
    }

    public Campaign addCampaignPayment(CampaignPayment campaignPayment) {
        this.campaignPayments.add(campaignPayment);
        campaignPayment.setCampaign(this);
        return this;
    }

    public Campaign removeCampaignPayment(CampaignPayment campaignPayment) {
        this.campaignPayments.remove(campaignPayment);
        campaignPayment.setCampaign(null);
        return this;
    }

    public void setCampaignPayments(Set<CampaignPayment> campaignPayments) {
        this.campaignPayments = campaignPayments;
    }

    public Set<CampaignInvestor> getCampaignInvestors() {
        return campaignInvestors;
    }

    public Campaign campaignInvestors(Set<CampaignInvestor> campaignInvestors) {
        this.campaignInvestors = campaignInvestors;
        return this;
    }

    public Campaign addCampaignInvestor(CampaignInvestor campaignInvestor) {
        this.campaignInvestors.add(campaignInvestor);
        campaignInvestor.setCampaign(this);
        return this;
    }

    public Campaign removeCampaignInvestor(CampaignInvestor campaignInvestor) {
        this.campaignInvestors.remove(campaignInvestor);
        campaignInvestor.setCampaign(null);
        return this;
    }

    public void setCampaignInvestors(Set<CampaignInvestor> campaignInvestors) {
        this.campaignInvestors = campaignInvestors;
    }

    public Set<CampaignTransaction> getCampaignTransactions() {
        return campaignTransactions;
    }

    public Campaign campaignTransactions(Set<CampaignTransaction> campaignTransactions) {
        this.campaignTransactions = campaignTransactions;
        return this;
    }

    public Campaign addCampaignTransaction(CampaignTransaction campaignTransaction) {
        this.campaignTransactions.add(campaignTransaction);
        campaignTransaction.setCampaign(this);
        return this;
    }

    public Campaign removeCampaignTransaction(CampaignTransaction campaignTransaction) {
        this.campaignTransactions.remove(campaignTransaction);
        campaignTransaction.setCampaign(null);
        return this;
    }

    public void setCampaignTransactions(Set<CampaignTransaction> campaignTransactions) {
        this.campaignTransactions = campaignTransactions;
    }

    public Set<CampaignCategory> getCampaignCategories() {
        return campaignCategories;
    }

    public Campaign campaignCategories(Set<CampaignCategory> campaignCategories) {
        this.campaignCategories = campaignCategories;
        return this;
    }

    public Campaign addCampaignCategory(CampaignCategory campaignCategory) {
        this.campaignCategories.add(campaignCategory);
        campaignCategory.setCampaign(this);
        return this;
    }

    public Campaign removeCampaignCategory(CampaignCategory campaignCategory) {
        this.campaignCategories.remove(campaignCategory);
        campaignCategory.setCampaign(null);
        return this;
    }

    public void setCampaignCategories(Set<CampaignCategory> campaignCategories) {
        this.campaignCategories = campaignCategories;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Campaign currency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public FundRaiser getFundRaiser() {
        return fundRaiser;
    }

    public Campaign fundRaiser(FundRaiser fundRaiser) {
        this.fundRaiser = fundRaiser;
        return this;
    }

    public void setFundRaiser(FundRaiser fundRaiser) {
        this.fundRaiser = fundRaiser;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Campaign)) {
            return false;
        }
        return id != null && id.equals(((Campaign) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Campaign{" +
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
            ", prospectusFileContentType='" + getProspectusFileContentType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
