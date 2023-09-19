package com.example.demo.repository.redis

import com.example.demo.entity.BlogCounter
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootTest
class BlogRedisRepositoryTest @Autowired constructor(
    var blogRedisRepository: BlogRedisRepository
){


    var log = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun getBlogTest() {
        var query = "김밥"
        val blog = blogRedisRepository.findById(query).orElse(BlogCounter(query, 0))
        log.info("blog {}", blog)
    }
}