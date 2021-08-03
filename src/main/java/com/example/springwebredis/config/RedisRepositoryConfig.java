package com.example.springwebredis.config;

import com.example.springwebredis.domain.RedisEntity;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@EnableRedisRepositories
public class RedisRepositoryConfig {

    /* application-redis.yml 내의 property 파일 설정을 읽어오는 클래스 */
    private final RedisProperties redisProperties;

    public RedisRepositoryConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    /* Redis Connector */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisProperties.getHost());
        redisStandaloneConfiguration.setPort(redisProperties.getPort());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, RedisEntity> redisTemplate() {
        final RedisTemplate<String, RedisEntity> redisTemplate = new RedisTemplate<>();

        /* valueOperations 사용하는 경우 redis-cli 에서 keys * 조회시 키 값들의 앞에 \xac\xed\x00\x05t\x00\x04 붙는거 제거 */
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        /* valueOperations 사용하는 경우 redis-cli 에서 get 'key' 조회 시 value 값에 \xac\xed\x00\x05t\x00\x04 붙는거 제거 */
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        /* hashOperations<String, Object, Object>를 사용하는 경우 두 번째 파라미터가 HashKey */
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        /* hashOperations<String, Object, Object>를 사용하는 경우 세 번째 파라미터가 HashValue */
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        /* 트랜잭션 */
        redisTemplate.setEnableTransactionSupport(true);

        /* redisTemplate은 redisConnectionFactory을 기반으로 돌아감 */
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setEnableDefaultSerializer(false);

        return redisTemplate;
    }
}
