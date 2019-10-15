package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.Bank} entity.
 */
public class BankDTO implements Serializable {

    private Long id;

    @Size(max = 10)
    private String bankCode;

    @Size(max = 60)
    private String bankName;

    @Size(max = 60)
    private String initialName;

    @Size(max = 3)
    private String biCode;

    @Size(max = 20)
    private String swiftCode;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getInitialName() {
        return initialName;
    }

    public void setInitialName(String initialName) {
        this.initialName = initialName;
    }

    public String getBiCode() {
        return biCode;
    }

    public void setBiCode(String biCode) {
        this.biCode = biCode;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankDTO bankDTO = (BankDTO) o;
        if (bankDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankDTO{" +
            "id=" + getId() +
            ", bankCode='" + getBankCode() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", initialName='" + getInitialName() + "'" +
            ", biCode='" + getBiCode() + "'" +
            ", swiftCode='" + getSwiftCode() + "'" +
            "}";
    }
}
