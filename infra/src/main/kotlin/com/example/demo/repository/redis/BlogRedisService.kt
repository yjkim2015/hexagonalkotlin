package com.example.demo.repository.redis

import com.example.demo.entity.BlogEntity
import com.example.demo.model.BlogRankingDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class BlogRedisService @Autowired constructor(
    private val blogRedisRepository: BlogRedisRepository,
    private val redisTemplate: RedisTemplate<String, String>
){

    private val zSetOperations = redisTemplate.opsForZSet()
    private val key = "ranking"
    private val start: Long = 0
    private val end: Long = 9
    private val incrementValue = 1.0

    fun saveBlog(blogEntity: BlogEntity) {
        blogRedisRepository.save(blogEntity)
    }

    fun getBlog(key: String): BlogEntity? {
        return blogRedisRepository.findByIdOrNull(key)
    }
    fun getBlogScore(query: String) : Double? {
        return zSetOperations.score(key, query)
    }

    fun incrementBlogScore(query: String) {
        zSetOperations.incrementScore(key, query, incrementValue)
    }
    fun getPupularBlogs(): List<BlogRankingDto>? {
        val reverseRangeWithScores: Set<ZSetOperations.TypedTuple<String>>? = zSetOperations.reverseRangeWithScores(key, 0, 9)

        return reverseRangeWithScores?.map { BlogRankingDto(it.value, it.score) }?.toList() ?: emptyList()
    }
}