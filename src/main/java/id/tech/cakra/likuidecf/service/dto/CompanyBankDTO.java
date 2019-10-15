package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.CompanyBank} entity.
 */
public class CompanyBankDTO implements Serializable {

    private Long id;

    @Size(max = 60)
    private String bankAccountNo;

    @Size(max = 100)
    private String bankAccountName;

    @Size(max = 100)
    private String bankBranch;

    @Size(max = 1)
    private String status;


    private Long bankId;

    private Long currencyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyBankDTO companyBankDTO = (CompanyBankDTO) o;
        if (companyBankDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyBankDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyBankDTO{" +
            "id=" + getId() +
            ", bankAccountNo='" + getBankAccountNo() + "'" +
            ", bankAccountName='" + getBankAccountName() + "'" +
            ", bankBranch='" + getBankBranch() + "'" +
            ", status='" + getStatus() + "'" +
            ", bank=" + getBankId() +
            ", currency=" + getCurrencyId() +
            "}";
    }
}
