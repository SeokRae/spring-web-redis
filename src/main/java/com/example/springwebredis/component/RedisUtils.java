package com.example.springwebredis.component;

import com.example.springwebredis.dto.RedisEntity;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisUtils {

    private final RedisTemplate<String, String> redisTemplate;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, RedisEntity> hashOperations;

    public RedisUtils(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean hasKey(String key, String hashKey) {
        return hashOperations.hasKey(key, hashKey);
    }

//    public AccountBasicInfo get(String key, String hashKey) {
//        return hashOperations.get(key, hashKey);
//    }
//
//    public void makeRefreshTokenAndExpiredAt(String signature, String accessToken, AccountBasicInfo accountBasicInfo) {
//        hashOperations.put(Constant.RedisConst.PREFIX_KEY + signature, accessToken, accountBasicInfo);
//        redisTemplate.expireAt(Constant.RedisConst.PREFIX_KEY + signature, getDate(Constant.RedisConst.REDIS_EXPIRED));
//    }
}
