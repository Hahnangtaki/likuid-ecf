package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.FundRaiser} entity.
 */
public class FundRaiserDTO implements Serializable {

    private Long id;

    @Size(max = 100)
    private String companyName;

    private Integer yearFounded;

    @Size(max = 100)
    private String website;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(Integer yearFounded) {
        this.yearFounded = yearFounded;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FundRaiserDTO fundRaiserDTO = (FundRaiserDTO) o;
        if (fundRaiserDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fundRaiserDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FundRaiserDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", yearFounded=" + getYearFounded() +
            ", website='" + getWebsite() + "'" +
            "}";
    }
}
