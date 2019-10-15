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
 * A Province.
 */
@Entity
@Table(name = "province")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "province_code", length = 10)
    private String provinceCode;

    @Size(max = 60)
    @Column(name = "province_name", length = 60)
    private String provinceName;

    @OneToMany(mappedBy = "province")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<City> cities = new HashSet<>();

    @OneToMany(mappedBy = "province")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorAddress> investorAddresses = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("provinces")
    private Country country;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public Province provinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
        return this;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public Province provinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Set<City> getCities() {
        return cities;
    }

    public Province cities(Set<City> cities) {
        this.cities = cities;
        return this;
    }

    public Province addCity(City city) {
        this.cities.add(city);
        city.setProvince(this);
        return this;
    }

    public Province removeCity(City city) {
        this.cities.remove(city);
        city.setProvince(null);
        return this;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Set<InvestorAddress> getInvestorAddresses() {
        return investorAddresses;
    }

    public Province investorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
        return this;
    }

    public Province addInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.add(investorAddress);
        investorAddress.setProvince(this);
        return this;
    }

    public Province removeInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.remove(investorAddress);
        investorAddress.setProvince(null);
        return this;
    }

    public void setInvestorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
    }

    public Country getCountry() {
        return country;
    }

    public Province country(Country country) {
        this.country = country;
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Province)) {
            return false;
        }
        return id != null && id.equals(((Province) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Province{" +
            "id=" + getId() +
            ", provinceCode='" + getProvinceCode() + "'" +
            ", provinceName='" + getProvinceName() + "'" +
            "}";
    }
}
