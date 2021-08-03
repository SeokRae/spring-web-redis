package com.example.springwebredis.controller;

import com.example.springwebredis.domain.RedisEntity;
import com.example.springwebredis.dto.RedisDto;
import com.example.springwebredis.dto.RedisResponseDto;
import com.example.springwebredis.dto.RedisSaveDto;
import com.example.springwebredis.service.RedisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @PostMapping
    public ResponseEntity<String> addData(@RequestBody RedisSaveDto saveDto) {

        RedisDto redisDto = RedisDto.toDto(saveDto);
        String savedDataId = redisService.addData(redisDto);

        return ResponseEntity.status(HttpStatus.OK).body(savedDataId);
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

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<RedisResponseDto> getData(@PathVariable String id) {

        RedisResponseDto data = redisService.getData(id);

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteData(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
