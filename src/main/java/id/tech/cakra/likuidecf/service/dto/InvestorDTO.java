package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.Investor} entity.
 */
public class InvestorDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String investorCode;

    @Size(max = 100)
    private String investorName;

    @Size(max = 1)
    private String investorType;


    private Long investorInstitutionId;

    private Long investorIndividuId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvestorCode() {
        return investorCode;
    }

    public void setInvestorCode(String investorCode) {
        this.investorCode = investorCode;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getInvestorType() {
        return investorType;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public Long getInvestorInstitutionId() {
        return investorInstitutionId;
    }

    public void setInvestorInstitutionId(Long investorInstitutionId) {
        this.investorInstitutionId = investorInstitutionId;
    }

    public Long getInvestorIndividuId() {
        return investorIndividuId;
    }

    public void setInvestorIndividuId(Long investorIndividuId) {
        this.investorIndividuId = investorIndividuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestorDTO investorDTO = (InvestorDTO) o;
        if (investorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestorDTO{" +
            "id=" + getId() +
            ", investorCode='" + getInvestorCode() + "'" +
            ", investorName='" + getInvestorName() + "'" +
            ", investorType='" + getInvestorType() + "'" +
            ", investorInstitution=" + getInvestorInstitutionId() +
            ", investorIndividu=" + getInvestorIndividuId() +
            "}";
    }
}
