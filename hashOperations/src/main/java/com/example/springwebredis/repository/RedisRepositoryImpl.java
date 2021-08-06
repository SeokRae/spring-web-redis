package com.example.springwebredis.repository;

import com.example.springwebredis.domain.RedisEntity;
import com.example.springwebredis.exception.RedisDataDeleteFailureException;
import com.example.springwebredis.exception.RedisDataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private final HashOperations<String, String, RedisEntity> hashOperations;

    public RedisRepositoryImpl(RedisTemplate<String, RedisEntity> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public RedisEntity findByIdHashOps(String id) {

        RedisEntity user = hashOperations.get("USER", id);

        return Optional.ofNullable(user)
                .orElseThrow(RedisDataNotFoundException::new);
    }

    @Override
    public Map<String, RedisEntity> findAllHashOps() {
        return hashOperations.entries("USER");
    }

    @Override
    public RedisEntity saveHashOps(RedisEntity entity) {
        hashOperations.put("USER", String.valueOf(entity.getId()), entity);
        return entity;
    }

    @Override
    public Long delete(String id) {
        if(hashKey(id)) {
            return hashOperations.delete("USER", id);
        }
        throw new RedisDataDeleteFailureException();
    }

    public boolean hashKey(String id) {
        return hashOperations.hasKey("USER", id);
    }

    @Override
    public Set<String> keysHashOps() {
        return hashOperations.keys("USER");
    }

    @Override
    public Long size() {
        return hashOperations.size("USER");
    }


}
