package id.tech.cakra.likuidecf.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.InvestorInstitution} entity.
 */
public class InvestorInstitutionDTO implements Serializable {

    private Long id;

    @Size(max = 15)
    private String sid;

    @Size(max = 100)
    private String companyName;

    @Size(max = 20)
    private String bicCode;

    private Long legalDomicileId;

    @Size(max = 100)
    private String npwp;

    private LocalDate npwpRegDate;

    @Size(max = 30)
    private String skd;

    private LocalDate skdExpDate;

    @Size(max = 10)
    private String companyEstablishPlce;

    private LocalDate companyEstablishDate;

    @Size(max = 1)
    private String businessType;

    @Size(max = 1)
    private String companyChracteristic;

    @Size(max = 30)
    private String fundSource;

    @Size(max = 120)
    private String fundSourceText;

    @Size(max = 120)
    private String articleAssociation;

    @Size(max = 120)
    private String bussinessRegNo;

    private Double financialAsset1;

    private Double financialAsset2;

    private Double financialAsset3;

    private Double operatingProfit1;

    private Double operatingProfit2;

    private Double operatingProfit3;

    @Size(max = 100)
    private String description;

    @Size(max = 100)
    private String investmentObjective;

    @Size(max = 15)
    private String directSid;

    @Size(max = 1)
    private String assetOwner;

    @Size(max = 1)
    private String supDocType;

    private LocalDate supDocExpDate;


    private Long taxId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBicCode() {
        return bicCode;
    }

    public void setBicCode(String bicCode) {
        this.bicCode = bicCode;
    }

    public Long getLegalDomicileId() {
        return legalDomicileId;
    }

    public void setLegalDomicileId(Long legalDomicileId) {
        this.legalDomicileId = legalDomicileId;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public LocalDate getNpwpRegDate() {
        return npwpRegDate;
    }

    public void setNpwpRegDate(LocalDate npwpRegDate) {
        this.npwpRegDate = npwpRegDate;
    }

    public String getSkd() {
        return skd;
    }

    public void setSkd(String skd) {
        this.skd = skd;
    }

    public LocalDate getSkdExpDate() {
        return skdExpDate;
    }

    public void setSkdExpDate(LocalDate skdExpDate) {
        this.skdExpDate = skdExpDate;
    }

    public String getCompanyEstablishPlce() {
        return companyEstablishPlce;
    }

    public void setCompanyEstablishPlce(String companyEstablishPlce) {
        this.companyEstablishPlce = companyEstablishPlce;
    }

    public LocalDate getCompanyEstablishDate() {
        return companyEstablishDate;
    }

    public void setCompanyEstablishDate(LocalDate companyEstablishDate) {
        this.companyEstablishDate = companyEstablishDate;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getCompanyChracteristic() {
        return companyChracteristic;
    }

    public void setCompanyChracteristic(String companyChracteristic) {
        this.companyChracteristic = companyChracteristic;
    }

    public String getFundSource() {
        return fundSource;
    }

    public void setFundSource(String fundSource) {
        this.fundSource = fundSource;
    }

    public String getFundSourceText() {
        return fundSourceText;
    }

    public void setFundSourceText(String fundSourceText) {
        this.fundSourceText = fundSourceText;
    }

    public String getArticleAssociation() {
        return articleAssociation;
    }

    public void setArticleAssociation(String articleAssociation) {
        this.articleAssociation = articleAssociation;
    }

    public String getBussinessRegNo() {
        return bussinessRegNo;
    }

    public void setBussinessRegNo(String bussinessRegNo) {
        this.bussinessRegNo = bussinessRegNo;
    }

    public Double getFinancialAsset1() {
        return financialAsset1;
    }

    public void setFinancialAsset1(Double financialAsset1) {
        this.financialAsset1 = financialAsset1;
    }

    public Double getFinancialAsset2() {
        return financialAsset2;
    }

    public void setFinancialAsset2(Double financialAsset2) {
        this.financialAsset2 = financialAsset2;
    }

    public Double getFinancialAsset3() {
        return financialAsset3;
    }

    public void setFinancialAsset3(Double financialAsset3) {
        this.financialAsset3 = financialAsset3;
    }

    public Double getOperatingProfit1() {
        return operatingProfit1;
    }

    public void setOperatingProfit1(Double operatingProfit1) {
        this.operatingProfit1 = operatingProfit1;
    }

    public Double getOperatingProfit2() {
        return operatingProfit2;
    }

    public void setOperatingProfit2(Double operatingProfit2) {
        this.operatingProfit2 = operatingProfit2;
    }

    public Double getOperatingProfit3() {
        return operatingProfit3;
    }

    public void setOperatingProfit3(Double operatingProfit3) {
        this.operatingProfit3 = operatingProfit3;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentObjective() {
        return investmentObjective;
    }

    public void setInvestmentObjective(String investmentObjective) {
        this.investmentObjective = investmentObjective;
    }

    public String getDirectSid() {
        return directSid;
    }

    public void setDirectSid(String directSid) {
        this.directSid = directSid;
    }

    public String getAssetOwner() {
        return assetOwner;
    }

    public void setAssetOwner(String assetOwner) {
        this.assetOwner = assetOwner;
    }

    public String getSupDocType() {
        return supDocType;
    }

    public void setSupDocType(String supDocType) {
        this.supDocType = supDocType;
    }

    public LocalDate getSupDocExpDate() {
        return supDocExpDate;
    }

    public void setSupDocExpDate(LocalDate supDocExpDate) {
        this.supDocExpDate = supDocExpDate;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestorInstitutionDTO investorInstitutionDTO = (InvestorInstitutionDTO) o;
        if (investorInstitutionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investorInstitutionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestorInstitutionDTO{" +
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
            ", tax=" + getTaxId() +
            "}";
    }
}
