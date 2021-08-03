package com.example.springwebredis.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RedisEntity implements Serializable {

    private String id;

    private String value;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSS", locale = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Builder
    public RedisEntity(String id, String value, LocalDateTime createdAt) {
        this.id = id;
        this.value = value;
        this.createdAt = createdAt;
    }
}
