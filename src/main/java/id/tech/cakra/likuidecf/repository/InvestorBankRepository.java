package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.InvestorBank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestorBank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorBankRepository extends JpaRepository<InvestorBank, Long> {

}
