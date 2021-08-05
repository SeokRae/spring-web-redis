package com.example.springwebredis.repository;

import com.example.springwebredis.domain.RedisEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

@Slf4j
@Repository
public class RedisRepositoryImpl implements RedisRepository {
    
    private final RedisTemplate<String, RedisEntity> redisTemplate;
    private final HashOperations<String, String, RedisEntity> hashOperations;

    public RedisRepositoryImpl(RedisTemplate<String, RedisEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public RedisEntity findById(String id) {
        RedisEntity user = hashOperations.get("USER", id);
        log.info("레디스 값 조회: {}", user);
        return user;
    }

    public Map<String, RedisEntity> findAllData() {
        Map<String, RedisEntity> users = hashOperations.entries("USER");
        return users;
    }

    public String save(RedisEntity entity) {
        hashOperations.put("USER", String.valueOf(entity.getId()), entity);
        return entity.getId();
    }

    public void delete(String id) {
        hashOperations.delete("USER", id);
    }

    @Override
    public Set<String> keys() {
        return hashOperations.keys("USER");
    }
}
