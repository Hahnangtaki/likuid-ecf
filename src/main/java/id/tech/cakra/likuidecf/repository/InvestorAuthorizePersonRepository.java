package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.InvestorAuthorizePerson;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestorAuthorizePerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorAuthorizePersonRepository extends JpaRepository<InvestorAuthorizePerson, Long> {

}
