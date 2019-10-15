package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.Tax} entity.
 */
public class TaxDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String taxCode;

    @Size(max = 30)
    private String shortDesc;

    @Size(max = 100)
    private String longDesc;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaxDTO taxDTO = (TaxDTO) o;
        if (taxDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taxDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaxDTO{" +
            "id=" + getId() +
            ", taxCode='" + getTaxCode() + "'" +
            ", shortDesc='" + getShortDesc() + "'" +
            ", longDesc='" + getLongDesc() + "'" +
            "}";
    }
}
