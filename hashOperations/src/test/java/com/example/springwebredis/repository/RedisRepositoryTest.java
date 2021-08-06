package com.example.springwebredis.repository;

import com.example.springwebredis.domain.RedisEntity;
import com.example.springwebredis.dto.RedisDto;
import com.example.springwebredis.exception.RedisDataNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisRepositoryTest {

    @Autowired
    private RedisRepository redisRepository;

    /* 테스트 종료 후 데이터 삭제 */
    @AfterEach
    void tearDown() {
        redisRepository.delete("USER");
    }

    @DisplayName("레디스 엔티티 등록 테스트")
    @Test
    void testCase1() {
        // service
        RedisEntity saveEntity = RedisDto.toEntity(
                RedisDto.builder()
                        .id(UUID.randomUUID().toString())
                        .value("데이터")
                        .build()
        );
        // repository
        redisRepository.saveHashOps(saveEntity);

        Long size = redisRepository.size();

        assertThat(size).isNotZero();
    }

    @DisplayName("레디스 데이터 잘못된 id로 검색할 때 테스트")
    @Test
    void testCase2() {
        // given
        RedisEntity saveEntity = RedisDto.toSaveEntity(
                UUID.randomUUID().toString(),
                RedisDto.builder()
                        .value("데이터")
                        .build()
        );

        // when
        RedisEntity redisEntity = redisRepository.saveHashOps(saveEntity);
        String id = redisEntity.getId();

        // then
        assertThatExceptionOfType(RedisDataNotFoundException.class)
                .isThrownBy(() -> redisRepository.findByIdHashOps(id + 1));
    }
}
