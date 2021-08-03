package com.example.springwebredis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @PostMapping
    public ResponseEntity<?> addData() {
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }

    @GetMapping
    public ResponseEntity<?> getAllData() {
        return ResponseEntity.status(HttpStatus.OK).body("{}");
    }
}
