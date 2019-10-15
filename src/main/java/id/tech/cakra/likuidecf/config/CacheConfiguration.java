package id.tech.cakra.likuidecf.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, id.tech.cakra.likuidecf.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, id.tech.cakra.likuidecf.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, id.tech.cakra.likuidecf.domain.User.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Authority.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.User.class.getName() + ".authorities");
            createCache(cm, id.tech.cakra.likuidecf.domain.MemberAccount.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.MemberAccount.class.getName() + ".otpHistories");
            createCache(cm, id.tech.cakra.likuidecf.domain.OtpHistory.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Category.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Category.class.getName() + ".campaignCategories");
            createCache(cm, id.tech.cakra.likuidecf.domain.CampaignCategory.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.FundRaiser.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.FundRaiser.class.getName() + ".fundRaiserBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.FundRaiser.class.getName() + ".campaigns");
            createCache(cm, id.tech.cakra.likuidecf.domain.Currency.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Currency.class.getName() + ".fundRaiserBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.Currency.class.getName() + ".companyBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.Currency.class.getName() + ".investorBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.Currency.class.getName() + ".campaigns");
            createCache(cm, id.tech.cakra.likuidecf.domain.Campaign.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Campaign.class.getName() + ".campaignPayments");
            createCache(cm, id.tech.cakra.likuidecf.domain.Campaign.class.getName() + ".campaignInvestors");
            createCache(cm, id.tech.cakra.likuidecf.domain.Campaign.class.getName() + ".campaignTransactions");
            createCache(cm, id.tech.cakra.likuidecf.domain.Campaign.class.getName() + ".campaignCategories");
            createCache(cm, id.tech.cakra.likuidecf.domain.CampaignPayment.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.CampaignTransaction.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Investor.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Investor.class.getName() + ".investorBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.Investor.class.getName() + ".investorAddresses");
            createCache(cm, id.tech.cakra.likuidecf.domain.Investor.class.getName() + ".investorAuthorizePeople");
            createCache(cm, id.tech.cakra.likuidecf.domain.Investor.class.getName() + ".campaignInvestors");
            createCache(cm, id.tech.cakra.likuidecf.domain.Investor.class.getName() + ".campaignTransactions");
            createCache(cm, id.tech.cakra.likuidecf.domain.InvestorBank.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.CompanyBank.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.CompanyBank.class.getName() + ".campaignPayments");
            createCache(cm, id.tech.cakra.likuidecf.domain.FundRaiserBank.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.FundRaiserBank.class.getName() + ".campaignPayments");
            createCache(cm, id.tech.cakra.likuidecf.domain.Bank.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Bank.class.getName() + ".fundRaiserBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.Bank.class.getName() + ".companyBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.Bank.class.getName() + ".investorBanks");
            createCache(cm, id.tech.cakra.likuidecf.domain.InvestorAuthorizePerson.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.CampaignInvestor.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.InvestorInstitution.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.InvestorIndividu.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Tax.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Tax.class.getName() + ".investorIndividus");
            createCache(cm, id.tech.cakra.likuidecf.domain.Tax.class.getName() + ".investorInstitutions");
            createCache(cm, id.tech.cakra.likuidecf.domain.InvestorAddress.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Country.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Country.class.getName() + ".provinces");
            createCache(cm, id.tech.cakra.likuidecf.domain.Country.class.getName() + ".investorAddresses");
            createCache(cm, id.tech.cakra.likuidecf.domain.Province.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.Province.class.getName() + ".cities");
            createCache(cm, id.tech.cakra.likuidecf.domain.Province.class.getName() + ".investorAddresses");
            createCache(cm, id.tech.cakra.likuidecf.domain.City.class.getName());
            createCache(cm, id.tech.cakra.likuidecf.domain.City.class.getName() + ".investorAddresses");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
