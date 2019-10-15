package id.tech.cakra.likuidecf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.likuidecf.domain.InvestorAddress} entity.
 */
public class InvestorAddressDTO implements Serializable {

    private Long id;

    @Size(max = 1)
    private String addressType;

    @Size(max = 60)
    private String address1;

    @Size(max = 60)
    private String address2;

    @Size(max = 60)
    private String address3;

    @Size(max = 5)
    private String postalCode;

    @Size(max = 30)
    private String phone;

    @Size(max = 30)
    private String mobilePhone;

    @Size(max = 256)
    private String email;

    @Size(max = 30)
    private String fax;


    private Long cityId;

    private Long provinceId;

    private Long countryId;

    private Long investorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InvestorAddressDTO investorAddressDTO = (InvestorAddressDTO) o;
        if (investorAddressDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investorAddressDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestorAddressDTO{" +
            "id=" + getId() +
            ", addressType='" + getAddressType() + "'" +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", address3='" + getAddress3() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", phone='" + getPhone() + "'" +
            ", mobilePhone='" + getMobilePhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", fax='" + getFax() + "'" +
            ", city=" + getCityId() +
            ", province=" + getProvinceId() +
            ", country=" + getCountryId() +
            ", investor=" + getInvestorId() +
            "}";
    }
}
