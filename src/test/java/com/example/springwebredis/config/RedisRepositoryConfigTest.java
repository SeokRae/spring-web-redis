package com.example.springwebredis.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class RedisRepositoryConfigTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DisplayName("GET: key 로 요청하는 기능 테스트")
    @Test
    void testCase1() {
        String key = "test_key";
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

    @DisplayName("SMEMBERS key 명령어 기능 테스트")
    @Test
    void testCase3() {
        String key = "key_set";
        SetOperations<String, String> setOps = redisTemplate.opsForSet();

        setOps.add(key, "d");
        setOps.add(key, "a");
        setOps.add(key, "t");
        setOps.add(key, "a");

        Set<String> members = setOps.members(key);
        System.out.println("members = " + members);

        Long size = setOps.size(key);
        System.out.println("size = " + size);

        Cursor<String> scan = setOps.scan(key,
                ScanOptions.scanOptions()
                        .match("*")
                        .count(3)
                        .build()
        );

        while (scan.hasNext()) {
            System.out.println("scan.next() = " + scan.next());
        }
    }

    @DisplayName("HASH key field")
    @Test
    void testCase4() {
        String key = "key_hash";

        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();

        hashOps.put(key, "hashKey_1", "value_1");
        hashOps.put(key, "hashKey_2", "value_2");
        hashOps.put(key, "hashKey_3", "value_3");

        Object hashValue1 = hashOps.get(key, "hashKey_1");

        System.out.println("hashValue1 = " + hashValue1);

        // key 엔트리 조회
        Map<Object, Object> entries = hashOps.entries(key);

        System.out.println("entries = " + entries);

        // key size 확인
        Long size = hashOps.size(key);

        System.out.println("size = " + size);
    }
}
