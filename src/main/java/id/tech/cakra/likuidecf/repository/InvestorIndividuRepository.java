package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.InvestorIndividu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestorIndividu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorIndividuRepository extends JpaRepository<InvestorIndividu, Long> {

}
