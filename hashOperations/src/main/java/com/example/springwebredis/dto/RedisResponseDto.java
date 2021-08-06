package com.example.springwebredis.dto;

import com.example.springwebredis.domain.RedisEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RedisResponseDto {
    private String id;
    private String value;
    private LocalDateTime createdAt;

    @Builder
    public RedisResponseDto(String id, String value, LocalDateTime createdAt) {
        this.id = id;
        this.value = value;
        this.createdAt = createdAt;
    }

    public static RedisResponseDto toResponse(RedisEntity redisEntity) {
        return RedisResponseDto.builder()
                .id(redisEntity.getId())
                .value(redisEntity.getValue())
                .createdAt(redisEntity.getCreatedAt())
                .build();
    }
}
