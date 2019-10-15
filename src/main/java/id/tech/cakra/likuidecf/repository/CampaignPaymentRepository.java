package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.CampaignPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CampaignPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignPaymentRepository extends JpaRepository<CampaignPayment, Long> {

}
