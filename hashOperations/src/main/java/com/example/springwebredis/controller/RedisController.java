package com.example.springwebredis.controller;

import com.example.springwebredis.domain.RedisEntity;
import com.example.springwebredis.dto.RedisDto;
import com.example.springwebredis.dto.RedisResponseDto;
import com.example.springwebredis.dto.RedisSaveDto;
import com.example.springwebredis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RedisEntity> addData(@RequestBody RedisSaveDto saveDto) {

        RedisDto redisDto = RedisDto.toDto(saveDto);
        RedisEntity savedEntity = redisService.addData(redisDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);

    }

    @GetMapping
    public ResponseEntity<Map<String, RedisEntity>> getAllData() {

        Map<String, RedisEntity> dataList = redisService.getAllData();

        return ResponseEntity.status(HttpStatus.OK).body(dataList);
    }

    @GetMapping(value = "/keys")
    public ResponseEntity<Set<String>> getKeys() {

        Set<String> keys = redisService.getKeys();

        return ResponseEntity.status(HttpStatus.OK).body(keys);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RedisResponseDto> getData(@PathVariable String id) {

        RedisResponseDto data = redisService.getData(id);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteData(@PathVariable String id) {

        Long deletedData = redisService.deleteData(id);

        return ResponseEntity.status(HttpStatus.OK).body(deletedData);
    }
}
