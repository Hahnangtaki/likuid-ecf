package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.InvestorInstitution;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestorInstitution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorInstitutionRepository extends JpaRepository<InvestorInstitution, Long> {

}
