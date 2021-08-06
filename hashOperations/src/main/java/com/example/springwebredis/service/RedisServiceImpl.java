package com.example.springwebredis.service;

import com.example.springwebredis.domain.RedisEntity;
import com.example.springwebredis.dto.RedisDto;
import com.example.springwebredis.dto.RedisResponseDto;
import com.example.springwebredis.repository.RedisRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 스프링 MVC구조로 구성한 레디스 접근 서비스 레이어
 */
@Service
public class RedisServiceImpl implements RedisService {

    private final RedisRepository redisRepository;

    public RedisServiceImpl(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public RedisEntity addData(RedisDto saveDto) {

        RedisEntity saveData = RedisDto.toSaveEntity(UUID.randomUUID().toString(), saveDto);

        return redisRepository.saveHashOps(saveData);
    }

    @Override
    public Map<String, RedisEntity> getAllData() {
        return redisRepository.findAllHashOps();
    }

    @Override
    public Set<String> getKeys() {
        return redisRepository.keysHashOps();
    }

    @Override
    public RedisResponseDto getData(String id) {

        RedisEntity redisEntity = redisRepository.findByIdHashOps(id);

        return RedisResponseDto.toResponse(redisEntity);
    }

    @Override
    public void deleteData(String id) {
        redisRepository.delete(id);
    }

}
