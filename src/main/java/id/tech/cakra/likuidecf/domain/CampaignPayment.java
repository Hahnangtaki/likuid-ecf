package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A CampaignPayment.
 */
@Entity
@Table(name = "campaign_payment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampaignPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "payment_code", length = 10)
    private String paymentCode;

    @Size(max = 200)
    @Column(name = "payment_desc", length = 200)
    private String paymentDesc;

    @Column(name = "amount")
    private Double amount;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("campaignPayments")
    private FundRaiserBank fundRaiserBank;

    @ManyToOne
    @JsonIgnoreProperties("campaignPayments")
    private Campaign campaign;

    @ManyToOne
    @JsonIgnoreProperties("campaignPayments")
    private CompanyBank companyBank;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public CampaignPayment paymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
        return this;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentDesc() {
        return paymentDesc;
    }

    public CampaignPayment paymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
        return this;
    }

    public void setPaymentDesc(String paymentDesc) {
        this.paymentDesc = paymentDesc;
    }

    public Double getAmount() {
        return amount;
    }

    public CampaignPayment amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public CampaignPayment status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FundRaiserBank getFundRaiserBank() {
        return fundRaiserBank;
    }

    public CampaignPayment fundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBank = fundRaiserBank;
        return this;
    }

    public void setFundRaiserBank(FundRaiserBank fundRaiserBank) {
        this.fundRaiserBank = fundRaiserBank;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignPayment campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public CompanyBank getCompanyBank() {
        return companyBank;
    }

    public CampaignPayment companyBank(CompanyBank companyBank) {
        this.companyBank = companyBank;
        return this;
    }

    public void setCompanyBank(CompanyBank companyBank) {
        this.companyBank = companyBank;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignPayment)) {
            return false;
        }
        return id != null && id.equals(((CampaignPayment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampaignPayment{" +
            "id=" + getId() +
            ", paymentCode='" + getPaymentCode() + "'" +
            ", paymentDesc='" + getPaymentDesc() + "'" +
            ", amount=" + getAmount() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
