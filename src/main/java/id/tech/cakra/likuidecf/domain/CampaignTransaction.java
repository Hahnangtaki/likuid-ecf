package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A CampaignTransaction.
 */
@Entity
@Table(name = "campaign_transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampaignTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "trans_code", length = 10)
    private String transCode;

    @Size(max = 200)
    @Column(name = "trans_desc", length = 200)
    private String transDesc;

    @Column(name = "trans_date")
    private LocalDate transDate;

    @Size(max = 1)
    @Column(name = "trans_type", length = 1)
    private String transType;

    @Column(name = "qty")
    private Long qty;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "unique_charges")
    private Double uniqueCharges;

    @Column(name = "net_amount")
    private Double netAmount;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

    @ManyToOne
    @JsonIgnoreProperties("campaignTransactions")
    private Investor investor;

    @ManyToOne
    @JsonIgnoreProperties("campaignTransactions")
    private Campaign campaign;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransCode() {
        return transCode;
    }

    public CampaignTransaction transCode(String transCode) {
        this.transCode = transCode;
        return this;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public CampaignTransaction transDesc(String transDesc) {
        this.transDesc = transDesc;
        return this;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public CampaignTransaction transDate(LocalDate transDate) {
        this.transDate = transDate;
        return this;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public String getTransType() {
        return transType;
    }

    public CampaignTransaction transType(String transType) {
        this.transType = transType;
        return this;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Long getQty() {
        return qty;
    }

    public CampaignTransaction qty(Long qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public CampaignTransaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUniqueCharges() {
        return uniqueCharges;
    }

    public CampaignTransaction uniqueCharges(Double uniqueCharges) {
        this.uniqueCharges = uniqueCharges;
        return this;
    }

    public void setUniqueCharges(Double uniqueCharges) {
        this.uniqueCharges = uniqueCharges;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public CampaignTransaction netAmount(Double netAmount) {
        this.netAmount = netAmount;
        return this;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return status;
    }

    public CampaignTransaction status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Investor getInvestor() {
        return investor;
    }

    public CampaignTransaction investor(Investor investor) {
        this.investor = investor;
        return this;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignTransaction campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignTransaction)) {
            return false;
        }
        return id != null && id.equals(((CampaignTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampaignTransaction{" +
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
            "}";
    }
}
