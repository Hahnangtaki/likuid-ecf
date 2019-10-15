package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.FundRaiser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FundRaiser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundRaiserRepository extends JpaRepository<FundRaiser, Long> {

}
