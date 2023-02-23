package com.example.springcacheableredisttl.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableCaching
public class CacheConfig {

    private final RedisProperties redisProperties;

    private final int DEFAULT_EXPIRE_SECONDS = 600;


    @Bean(name = "cacheManager")
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(connectionFactory)
                .cacheDefaults(getRedisCacheConfiguration())
                .withInitialCacheConfigurations(getStringRedisCacheConfigurationMap())
                .build();
    }

    private RedisCacheConfiguration getRedisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(DEFAULT_EXPIRE_SECONDS))
                .computePrefixWith(CacheKeyPrefix.simple())
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    private Map<String, RedisCacheConfiguration> getStringRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // ApiAccessInfo
        cacheConfigurations.put("num", RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(30)));

        // Menus
//        cacheConfigurations.put(Menus, RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(MENUS_EXPIRE_SECONDS)));
        return cacheConfigurations;
    }

}
