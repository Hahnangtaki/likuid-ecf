package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.Investor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Investor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

}
