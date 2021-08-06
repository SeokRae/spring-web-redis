package com.example.springwebredis.repository;

import com.example.springwebredis.domain.RedisEntity;

import java.util.Map;
import java.util.Set;

public interface RedisRepository {
    RedisEntity findByIdHashOps(String id);

    Map<String, RedisEntity> findAllHashOps();

    RedisEntity saveHashOps(RedisEntity entity);

    Long delete(String id);

    Set<String> keysHashOps();

    Long size();
}
