package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A InvestorInstitution.
 */
@Entity
@Table(name = "investor_institution")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvestorInstitution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 15)
    @Column(name = "sid", length = 15)
    private String sid;

    @Size(max = 100)
    @Column(name = "company_name", length = 100)
    private String companyName;

    @Size(max = 20)
    @Column(name = "bic_code", length = 20)
    private String bicCode;

    @Column(name = "legal_domicile_id")
    private Long legalDomicileId;

    @Size(max = 100)
    @Column(name = "npwp", length = 100)
    private String npwp;

    @Column(name = "npwp_reg_date")
    private LocalDate npwpRegDate;

    @Size(max = 30)
    @Column(name = "skd", length = 30)
    private String skd;

    @Column(name = "skd_exp_date")
    private LocalDate skdExpDate;

    @Size(max = 10)
    @Column(name = "company_establish_plce", length = 10)
    private String companyEstablishPlce;

    @Column(name = "company_establish_date")
    private LocalDate companyEstablishDate;

    @Size(max = 1)
    @Column(name = "business_type", length = 1)
    private String businessType;

    @Size(max = 1)
    @Column(name = "company_chracteristic", length = 1)
    private String companyChracteristic;

    @Size(max = 30)
    @Column(name = "fund_source", length = 30)
    private String fundSource;

    @Size(max = 120)
    @Column(name = "fund_source_text", length = 120)
    private String fundSourceText;

    @Size(max = 120)
    @Column(name = "article_association", length = 120)
    private String articleAssociation;

    @Size(max = 120)
    @Column(name = "bussiness_reg_no", length = 120)
    private String bussinessRegNo;

    @Column(name = "financial_asset_1")
    private Double financialAsset1;

    @Column(name = "financial_asset_2")
    private Double financialAsset2;

    @Column(name = "financial_asset_3")
    private Double financialAsset3;

    @Column(name = "operating_profit_1")
    private Double operatingProfit1;

    @Column(name = "operating_profit_2")
    private Double operatingProfit2;

    @Column(name = "operating_profit_3")
    private Double operatingProfit3;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @Size(max = 100)
    @Column(name = "investment_objective", length = 100)
    private String investmentObjective;

    @Size(max = 15)
    @Column(name = "direct_sid", length = 15)
    private String directSid;

    @Size(max = 1)
    @Column(name = "asset_owner", length = 1)
    private String assetOwner;

    @Size(max = 1)
    @Column(name = "sup_doc_type", length = 1)
    private String supDocType;

    @Column(name = "sup_doc_exp_date")
    private LocalDate supDocExpDate;

    @OneToOne(mappedBy = "investorInstitution")
    @JsonIgnore
    private Investor investor;

    @ManyToOne
    @JsonIgnoreProperties("investorInstitutions")
    private Tax tax;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public InvestorInstitution sid(String sid) {
        this.sid = sid;
        return this;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public InvestorInstitution companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBicCode() {
        return bicCode;
    }

    public InvestorInstitution bicCode(String bicCode) {
        this.bicCode = bicCode;
        return this;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }

    public Long getLegalDomicileId() {
        return legalDomicileId;
    }

    public InvestorInstitution legalDomicileId(Long legalDomicileId) {
        this.legalDomicileId = legalDomicileId;
        return this;
    }

    public void setLegalDomicileId(Long legalDomicileId) {
        this.legalDomicileId = legalDomicileId;
    }

    public String getNpwp() {
        return npwp;
    }

    public InvestorInstitution npwp(String npwp) {
        this.npwp = npwp;
        return this;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public InvestorInstitution npwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
        return this;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getSkd() {
        return skd;
    }

    public InvestorInstitution skd(String skd) {
        this.skd = skd;
        return this;
    }

    public void setSkd(String skd) {
        this.skd = skd;
    }

    public LocalDate getSkdExpDate() {
        return skdExpDate;
    }

    public InvestorInstitution skdExpDate(LocalDate skdExpDate) {
        this.skdExpDate = skdExpDate;
        return this;
    }

    public void setSkdExpDate(LocalDate skdExpDate) {
        this.skdExpDate = skdExpDate;
    }

    public String getCompanyEstablishPlce() {
        return companyEstablishPlce;
    }

    public InvestorInstitution companyEstablishPlce(String companyEstablishPlce) {
        this.companyEstablishPlce = companyEstablishPlce;
        return this;
    }

    public void setCompanyEstablishPlce(String companyEstablishPlce) {
        this.companyEstablishPlce = companyEstablishPlce;
    }

    public LocalDate getCompanyEstablishDate() {
        return companyEstablishDate;
    }

    public InvestorInstitution companyEstablishDate(LocalDate companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
        return this;
    }

    public void setCompanyEstablishDate(LocalDate companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public InvestorInstitution businessType(String businessType) {
        this.businessType = businessType;
        return this;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyChracteristic() {
        return companyChracteristic;
    }

    public InvestorInstitution companyChracteristic(String companyChracteristic) {
        this.companyChracteristic = companyChracteristic;
        return this;
    }

    public void setCompanyChracteristic(String companyChracteristic) {
        this.companyChracteristic = companyChracteristic;
    }

    public String getFundSource() {
        return fundSource;
    }

    public InvestorInstitution fundSource(String fundSource) {
        this.fundSource = fundSource;
        return this;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getFundSourceText() {
        return fundSourceText;
    }

    public InvestorInstitution fundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
        return this;
    }

    public void setFundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
    }

    public String getArticleAssociation() {
        return articleAssociation;
    }

    public InvestorInstitution articleAssociation(String articleAssociation) {
        this.articleAssociation = articleAssociation;
        return this;
    }

    public void setArticleAssociation(String articleAssociation) {
        this.articleAssociation = articleAssociation;
    }

    public String getBussinessRegNo() {
        return bussinessRegNo;
    }

    public InvestorInstitution bussinessRegNo(String bussinessRegNo) {
        this.bussinessRegNo = bussinessRegNo;
        return this;
    }

    public void setBussinessRegNo(String bussinessRegNo) {
        this.bussinessRegNo = bussinessRegNo;
    }

    public Double getFinancialAsset1() {
        return financialAsset1;
    }

    public InvestorInstitution financialAsset1(Double financialAsset1) {
        this.financialAsset1 = financialAsset1;
        return this;
    }

    public void setFinancialAsset1(Double financialAsset1) {
        this.financialAsset1 = financialAsset1;
    }

    public Double getFinancialAsset2() {
        return financialAsset2;
    }

    public InvestorInstitution financialAsset2(Double financialAsset2) {
        this.financialAsset2 = financialAsset2;
        return this;
    }

    public void setFinancialAsset2(Double financialAsset2) {
        this.financialAsset2 = financialAsset2;
    }

    public Double getFinancialAsset3() {
        return financialAsset3;
    }

    public InvestorInstitution financialAsset3(Double financialAsset3) {
        this.financialAsset3 = financialAsset3;
        return this;
    }

    public void setFinancialAsset3(Double financialAsset3) {
        this.financialAsset3 = financialAsset3;
    }

    public Double getOperatingProfit1() {
        return operatingProfit1;
    }

    public InvestorInstitution operatingProfit1(Double operatingProfit1) {
        this.operatingProfit1 = operatingProfit1;
        return this;
    }

    public void setOperatingProfit1(Double operatingProfit1) {
        this.operatingProfit1 = operatingProfit1;
    }

    public Double getOperatingProfit2() {
        return operatingProfit2;
    }

    public InvestorInstitution operatingProfit2(Double operatingProfit2) {
        this.operatingProfit2 = operatingProfit2;
        return this;
    }

    public void setOperatingProfit2(Double operatingProfit2) {
        this.operatingProfit2 = operatingProfit2;
    }

    public Double getOperatingProfit3() {
        return operatingProfit3;
    }

    public InvestorInstitution operatingProfit3(Double operatingProfit3) {
        this.operatingProfit3 = operatingProfit3;
        return this;
    }

    public void setOperatingProfit3(Double operatingProfit3) {
        this.operatingProfit3 = operatingProfit3;
    }

    public String getDescription() {
        return description;
    }

    public InvestorInstitution description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public InvestorInstitution investmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
        return this;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getDirectSid() {
        return directSid;
    }

    public InvestorInstitution directSid(String directSid) {
        this.directSid = directSid;
        return this;
    }

    public void setDirectSid(String directSid) {
        this.directSid = directSid;
    }

    public String getAssetOwner() {
        return assetOwner;
    }

    public InvestorInstitution assetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
        return this;
    }

    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    public String getSupDocType() {
        return supDocType;
    }

    public InvestorInstitution supDocType(String supDocType) {
        this.supDocType = supDocType;
        return this;
    }

    public void setSupDocType(String supDocType) {
        this.supDocType = supDocType;
    }

    public LocalDate getSupDocExpDate() {
        return supDocExpDate;
    }

    public InvestorInstitution supDocExpDate(LocalDate supDocExpDate) {
        this.supDocExpDate = supDocExpDate;
        return this;
    }

    public void setSupDocExpDate(LocalDate supDocExpDate) {
        this.supDocExpDate = supDocExpDate;
    }

    public Investor getInvestor() {
        return investor;
    }

    public InvestorInstitution investor(Investor investor) {
        this.investor = investor;
        return this;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Tax getTax() {
        return tax;
    }

    public InvestorInstitution tax(Tax tax) {
        this.tax = tax;
        return this;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvestorInstitution)) {
            return false;
        }
        return id != null && id.equals(((InvestorInstitution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvestorInstitution{" +
            "id=" + getId() +
            ", sid='" + getSid() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", bicCode='" + getBicCode() + "'" +
            ", legalDomicileId=" + getLegalDomicileId() +
            ", npwp='" + getNpwp() + "'" +
            ", npwpRegDate='" + getNpwpRegDate() + "'" +
            ", skd='" + getSkd() + "'" +
            ", skdExpDate='" + getSkdExpDate() + "'" +
            ", companyEstablishPlce='" + getCompanyEstablishPlce() + "'" +
            ", companyEstablishDate='" + getCompanyEstablishDate() + "'" +
            ", businessType='" + getBusinessType() + "'" +
            ", companyChracteristic='" + getCompanyChracteristic() + "'" +
            ", fundSource='" + getFundSource() + "'" +
            ", fundSourceText='" + getFundSourceText() + "'" +
            ", articleAssociation='" + getArticleAssociation() + "'" +
            ", bussinessRegNo='" + getBussinessRegNo() + "'" +
            ", financialAsset1=" + getFinancialAsset1() +
            ", financialAsset2=" + getFinancialAsset2() +
            ", financialAsset3=" + getFinancialAsset3() +
            ", operatingProfit1=" + getOperatingProfit1() +
            ", operatingProfit2=" + getOperatingProfit2() +
            ", operatingProfit3=" + getOperatingProfit3() +
            ", description='" + getDescription() + "'" +
            ", investmentObjective='" + getInvestmentObjective() + "'" +
            ", directSid='" + getDirectSid() + "'" +
            ", assetOwner='" + getAssetOwner() + "'" +
            ", supDocType='" + getSupDocType() + "'" +
            ", supDocExpDate='" + getSupDocExpDate() + "'" +
            "}";
    }
}
