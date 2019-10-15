package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.MemberAccount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MemberAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MemberAccountRepository extends JpaRepository<MemberAccount, Long> {

}
