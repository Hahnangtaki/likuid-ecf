package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.CampaignInvestor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CampaignInvestor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignInvestorRepository extends JpaRepository<CampaignInvestor, Long> {

}
