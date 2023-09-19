package com.example.demo.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.annotation.Id

@RedisHash("blogCounter")
data class BlogCounter(
    @Id
    var query: String,
    var value: Long?
)