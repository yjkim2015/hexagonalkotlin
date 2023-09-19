package com.example.demo.repository.redis

import com.example.demo.entity.BlogCounter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service


@Service
class BlogRedisService @Autowired constructor(
    private val blogRedisRepository: BlogRedisRepository,
    private val redisTemplate: RedisTemplate<String, Long>
){

    private val valueOperations = redisTemplate.opsForValue()

    fun getBlogCounter(query: String): BlogCounter {
        return blogRedisRepository.findById(query).orElse(BlogCounter(query, 0))
    }

    fun incrementBlogCounter(query: String): Long? {
        val currentValue = valueOperations.increment(query, 1)
        val counterEntity = getBlogCounter(query)
        counterEntity.value = currentValue
        blogRedisRepository.save(counterEntity)
        return currentValue
    }
}