package com.example.springwebredis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash("redisEntity")
public class RedisEntity {
    @Id
    private Long id;
    private String value;

    @Builder
    public RedisEntity(Long id, String value) {
        this.id = id;
        this.value = value;
    }
}
