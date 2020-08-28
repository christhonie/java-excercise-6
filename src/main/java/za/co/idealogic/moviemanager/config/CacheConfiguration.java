package za.co.idealogic.moviemanager.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

import za.co.idealogic.moviemanager.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration() {
        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(200))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(3600)))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, za.co.idealogic.moviemanager.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, za.co.idealogic.moviemanager.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, za.co.idealogic.moviemanager.domain.User.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Authority.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.User.class.getName() + ".authorities");
            createCache(cm, za.co.idealogic.moviemanager.domain.Venue.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Genre.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Person.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Person.class.getName() + ".movies");
            createCache(cm, za.co.idealogic.moviemanager.domain.Movie.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Movie.class.getName() + ".actors");
            createCache(cm, za.co.idealogic.moviemanager.domain.Cinema.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Seat.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Screening.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Reservation.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Booking.class.getName());
            createCache(cm, za.co.idealogic.moviemanager.domain.Booking.class.getName() + ".reservations");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
