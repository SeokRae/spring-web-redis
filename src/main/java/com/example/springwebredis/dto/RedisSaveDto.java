package com.example.springwebredis.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RedisSaveDto {
    private String id;
    private String value;
    private LocalDateTime createdAt;
}
