package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "country_code", length = 10)
    private String countryCode;

    @Size(max = 60)
    @Column(name = "country_name", length = 60)
    private String countryName;

    @Size(max = 60)
    @Column(name = "nationality", length = 60)
    private String nationality;

    @OneToMany(mappedBy = "country")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Province> provinces = new HashSet<>();

    @OneToMany(mappedBy = "country")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorAddress> investorAddresses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Country countryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public Country countryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getNationality() {
        return nationality;
    }

    public Country nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Set<Province> getProvinces() {
        return provinces;
    }

    public Country provinces(Set<Province> provinces) {
        this.provinces = provinces;
        return this;
    }

    public Country addProvince(Province province) {
        this.provinces.add(province);
        province.setCountry(this);
        return this;
    }

    public Country removeProvince(Province province) {
        this.provinces.remove(province);
        province.setCountry(null);
        return this;
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }

    public Set<InvestorAddress> getInvestorAddresses() {
        return investorAddresses;
    }

    public Country investorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
        return this;
    }

    public Country addInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.add(investorAddress);
        investorAddress.setCountry(this);
        return this;
    }

    public Country removeInvestorAddress(InvestorAddress investorAddress) {
        this.investorAddresses.remove(investorAddress);
        investorAddress.setCountry(null);
        return this;
    }

    public void setInvestorAddresses(Set<InvestorAddress> investorAddresses) {
        this.investorAddresses = investorAddresses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        return id != null && id.equals(((Country) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Country{" +
            "id=" + getId() +
            ", countryCode='" + getCountryCode() + "'" +
            ", countryName='" + getCountryName() + "'" +
            ", nationality='" + getNationality() + "'" +
            "}";
    }
}
