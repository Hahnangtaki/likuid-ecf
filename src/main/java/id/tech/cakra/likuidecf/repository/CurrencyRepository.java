package id.tech.cakra.likuidecf.repository;
import id.tech.cakra.likuidecf.domain.Currency;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Currency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
