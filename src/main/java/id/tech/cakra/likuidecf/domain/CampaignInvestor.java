package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CampaignInvestor.
 */
@Entity
@Table(name = "campaign_investor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampaignInvestor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "qty")
    private Long qty;

    @Column(name = "fund_amount")
    private Double fundAmount;

    @ManyToOne
    @JsonIgnoreProperties("campaignInvestors")
    private Investor investor;

    @ManyToOne
    @JsonIgnoreProperties("campaignInvestors")
    private Campaign campaign;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQty() {
        return qty;
    }

    public CampaignInvestor qty(Long qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getFundAmount() {
        return fundAmount;
    }

    public CampaignInvestor fundAmount(Double fundAmount) {
        this.fundAmount = fundAmount;
        return this;
    }

    public void setFundAmount(Double fundAmount) {
        this.fundAmount = fundAmount;
    }

    public Investor getInvestor() {
        return investor;
    }

    public CampaignInvestor investor(Investor investor) {
        this.investor = investor;
        return this;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignInvestor campaign(Campaign campaign) {
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
        if (!(o instanceof CampaignInvestor)) {
            return false;
        }
        return id != null && id.equals(((CampaignInvestor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampaignInvestor{" +
            "id=" + getId() +
            ", qty=" + getQty() +
            ", fundAmount=" + getFundAmount() +
            "}";
    }
}
