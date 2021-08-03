package com.example.springwebredis.repository;

import com.example.springwebredis.domain.RedisEntity;

import java.util.Map;
import java.util.Set;

public interface RedisRepository {
    RedisEntity findById(String id);

    Map<String, RedisEntity> findAllData();

    String save(RedisEntity entity);

    void delete(String id);

    Set<String> keys();
}
