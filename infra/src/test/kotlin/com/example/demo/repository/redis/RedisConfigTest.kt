package com.example.demo.repository.redis

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class RedisConfigTest @Autowired constructor(
    private val redisTemplate: RedisTemplate<String, Any>
){

    var log = LoggerFactory.getLogger(this.javaClass)

    @DisplayName("string 기반의 key")
    @Test
    fun stringCacheTest() {
        val valueOperations = redisTemplate.opsForValue()
        val key = "stringKey"
        val value = "goodall"

        // 삽입
        valueOperations[key] = "goodall"

        // 조회
        log.info("{}", valueOperations[key])
    }
}