package com.example.springwebredis.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisRepositoryConfigTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DisplayName("GET: key 로 요청하는 기능 테스트")
    @Test
    void testCase1() {
        String key  = "test_key";
        String value = "value_1";

        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();

        valueOps.set(key, value);
        String expected = valueOps.get(key);

        assertThat(value).isEqualTo(expected);
    }

    @DisplayName("INDEX key index , LRANGE key start, stop 명령어 기능 테스트")
    @Test
    void testCase2() {
        String key = "key_list";
        ListOperations<String, String> listOps = redisTemplate.opsForList();

        listOps.rightPushIfPresent(key, "data_1");
        listOps.rightPushIfPresent(key, "data_2");
        listOps.rightPushIfPresent(key, "data_3");

        listOps.rightPushAll(key, "data_4", "data_5");

        String index_1 = listOps.index(key, 1);

        System.out.println("index_1 = " + index_1);

        Long size = listOps.size(key);
        System.out.println("size = " + size);

        List<String> range = listOps.range(key, 0, 3);

        System.out.println("range = " + range);
    }
}
