package com.example.demo.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.TimeToLive

@RedisHash("BlogEntity")
data class BlogEntity(
    @Id
    var query: String,
    var value: Any?,
)