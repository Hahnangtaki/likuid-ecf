package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.Bank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}
