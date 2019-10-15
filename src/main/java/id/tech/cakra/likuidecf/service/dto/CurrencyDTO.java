package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.Currency} entity.
 */
public class CurrencyDTO implements Serializable {

    private Long id;

    @Size(max = 5)
    private String currencyCode;

    @Size(max = 100)
    private String currencyName;

    @Size(max = 5)
    private String currencySymbol;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CurrencyDTO currencyDTO = (CurrencyDTO) o;
        if (currencyDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), currencyDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CurrencyDTO{" +
            "id=" + getId() +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", currencyName='" + getCurrencyName() + "'" +
            ", currencySymbol='" + getCurrencySymbol() + "'" +
            "}";
    }
}
