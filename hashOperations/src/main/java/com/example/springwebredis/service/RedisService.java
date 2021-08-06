package com.example.springwebredis.service;

import com.example.springwebredis.domain.RedisEntity;
import com.example.springwebredis.dto.RedisDto;
import com.example.springwebredis.dto.RedisResponseDto;

import java.util.Map;
import java.util.Set;

public interface RedisService {
    RedisEntity addData(RedisDto saveDto);

    Map<String, RedisEntity> getAllData();

    Set<String> getKeys();

    RedisResponseDto getData(String id);

    Long deleteData(String id);
}
