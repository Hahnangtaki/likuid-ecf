package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.FundRaiserBank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FundRaiserBank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundRaiserBankRepository extends JpaRepository<FundRaiserBank, Long> {

}
