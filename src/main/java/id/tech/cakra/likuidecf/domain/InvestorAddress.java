package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A InvestorAddress.
 */
@Entity
@Table(name = "investor_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvestorAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1)
    @Column(name = "address_type", length = 1)
    private String addressType;

    @Size(max = 60)
    @Column(name = "address_1", length = 60)
    private String address1;

    @Size(max = 60)
    @Column(name = "address_2", length = 60)
    private String address2;

    @Size(max = 60)
    @Column(name = "address_3", length = 60)
    private String address3;

    @Size(max = 5)
    @Column(name = "postal_code", length = 5)
    private String postalCode;

    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

    @Size(max = 30)
    @Column(name = "mobile_phone", length = 30)
    private String mobilePhone;

    @Size(max = 256)
    @Column(name = "email", length = 256)
    private String email;

    @Size(max = 30)
    @Column(name = "fax", length = 30)
    private String fax;

    @ManyToOne
    @JsonIgnoreProperties("investorAddresses")
    private City city;

    @ManyToOne
    @JsonIgnoreProperties("investorAddresses")
    private Province province;

    @ManyToOne
    @JsonIgnoreProperties("investorAddresses")
    private Country country;

    @ManyToOne
    @JsonIgnoreProperties("investorAddresses")
    private Investor investor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressType() {
        return addressType;
    }

    public InvestorAddress addressType(String addressType) {
        this.addressType = addressType;
        return this;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress1() {
        return address1;
    }

    public InvestorAddress address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public InvestorAddress address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public InvestorAddress address3(String address3) {
        this.address3 = address3;
        return this;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public InvestorAddress postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public InvestorAddress phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public InvestorAddress mobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public InvestorAddress email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public InvestorAddress fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public City getCity() {
        return city;
    }

    public InvestorAddress city(City city) {
        this.city = city;
        return this;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Province getProvince() {
        return province;
    }

    public InvestorAddress province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Country getCountry() {
        return country;
    }

    public InvestorAddress country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Investor getInvestor() {
        return investor;
    }

    public InvestorAddress investor(Investor investor) {
        this.investor = investor;
        return this;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvestorAddress)) {
            return false;
        }
        return id != null && id.equals(((InvestorAddress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvestorAddress{" +
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
            "}";
    }
}
