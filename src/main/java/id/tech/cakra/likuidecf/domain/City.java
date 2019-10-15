package id.tech.cakra.likuidecf.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A City.
 */
@Entity
@Table(name = "city")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "city_code", length = 10)
    private String cityCode;

    @Size(max = 60)
    @Column(name = "city_name", length = 60)
    private String cityName;

    @OneToMany(mappedBy = "city")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorAddress> investorAddresses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("cities")
    private Province province;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public City cityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public City cityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Set<InvestorAddress> getInvestorAddresses() {
        return investorAddresses;
    }

    public City investorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
        return this;
    }

    public City addInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.add(investorAddress);
        investorAddress.setCity(this);
        return this;
    }

    public City removeInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.remove(investorAddress);
        investorAddress.setCity(null);
        return this;
    }

    public void setInvestorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
    }

    public Province getProvince() {
        return province;
    }

    public City province(Province province) {
        this.province = province;
        return this;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof City)) {
            return false;
        }
        return id != null && id.equals(((City) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "City{" +
            "id=" + getId() +
            ", cityCode='" + getCityCode() + "'" +
            ", cityName='" + getCityName() + "'" +
            "}";
    }
}
