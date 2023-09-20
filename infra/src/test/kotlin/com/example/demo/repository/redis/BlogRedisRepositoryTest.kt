package com.example.demo.repository.redis

import com.example.demo.entity.BlogEntity
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BlogRedisRepositoryTest {

    @Autowired
    lateinit var blogRedisRepository: BlogRedisRepository

    var log = LoggerFactory.getLogger(this.javaClass)
    @Test
    fun getBlogTest() {
        var query = "김밥"
        val blog = blogRedisRepository.findById(query).orElse(BlogEntity(query, null))
        log.info("blog {}", blog)
    }
}