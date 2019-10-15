package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.InvestorAddress;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the InvestorAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestorAddressRepository extends JpaRepository<InvestorAddress, Long> {

}
