package id.tech.cakra.likuidecf.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tax.
 */
@Entity
@Table(name = "tax")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "tax_code", length = 10)
    private String taxCode;

    @Size(max = 30)
    @Column(name = "short_desc", length = 30)
    private String shortDesc;

    @Size(max = 100)
    @Column(name = "long_desc", length = 100)
    private String longDesc;

    @OneToMany(mappedBy = "tax")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorIndividu> investorIndividus = new HashSet<>();

    @OneToMany(mappedBy = "tax")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestorInstitution> investorInstitutions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public Tax taxCode(String taxCode) {
        this.taxCode = taxCode;
        return this;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public Tax shortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
        return this;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public Tax longDesc(String longDesc) {
        this.longDesc = longDesc;
        return this;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public Set<InvestorIndividu> getInvestorIndividus() {
        return investorIndividus;
    }

    public Tax investorIndividus(Set<InvestorIndividu> investorIndividus) {
        this.investorIndividus = investorIndividus;
        return this;
    }

    public Tax addInvestorIndividu(InvestorIndividu investorIndividu) {
        this.investorIndividus.add(investorIndividu);
        investorIndividu.setTax(this);
        return this;
    }

    public Tax removeInvestorIndividu(InvestorIndividu investorIndividu) {
        this.investorIndividus.remove(investorIndividu);
        investorIndividu.setTax(null);
        return this;
    }

    public void setInvestorIndividus(Set<InvestorIndividu> investorIndividus) {
        this.investorIndividus = investorIndividus;
    }

    public Set<InvestorInstitution> getInvestorInstitutions() {
        return investorInstitutions;
    }

    public Tax investorInstitutions(Set<InvestorInstitution> investorInstitutions) {
        this.investorInstitutions = investorInstitutions;
        return this;
    }

    public Tax addInvestorInstitution(InvestorInstitution investorInstitution) {
        this.investorInstitutions.add(investorInstitution);
        investorInstitution.setTax(this);
        return this;
    }

    public Tax removeInvestorInstitution(InvestorInstitution investorInstitution) {
        this.investorInstitutions.remove(investorInstitution);
        investorInstitution.setTax(null);
        return this;
    }

    public void setInvestorInstitutions(Set<InvestorInstitution> investorInstitutions) {
        this.investorInstitutions = investorInstitutions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tax)) {
            return false;
        }
        return id != null && id.equals(((Tax) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tax{" +
            "id=" + getId() +
            ", taxCode='" + getTaxCode() + "'" +
            ", shortDesc='" + getShortDesc() + "'" +
            ", longDesc='" + getLongDesc() + "'" +
            "}";
    }
}
