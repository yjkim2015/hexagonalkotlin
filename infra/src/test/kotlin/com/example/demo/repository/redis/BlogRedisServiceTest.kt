package com.example.demo.repository.redis

import com.example.demo.entity.BlogEntity
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.concurrent.thread

@SpringBootTest
class BlogRedisServiceTest
{
    @Autowired
    lateinit var blogRedisService: BlogRedisService

    var log = LoggerFactory.getLogger(this.javaClass)

    val query = "김밥"

    @Test
    fun saveBlogTest() {
        blogRedisService.saveBlog(BlogEntity(query, "testetestetest"))

        val blog = blogRedisService.getBlog(query)

        log.info("blog {}", blog)
    }

    @Test
    fun incrementBlogScoreTest() {
        blogRedisService.incrementBlogScore(query)
        val blogScore = blogRedisService.getBlogScore(query)
        log.info("blogScore {}", blogScore)
    }

    @Test
    fun getPopularBlogScoreTest() {
        for (i in 1..100) {
            if (i % 1 == 0){
                blogRedisService.incrementBlogScore("1")
            }
            if (i % 2 == 0) {
                blogRedisService.incrementBlogScore("2")
            }
            if (i % 3 == 0) {
                blogRedisService.incrementBlogScore("3")
            }
            if (i % 4 == 0) {
                blogRedisService.incrementBlogScore("4")
            }
            if (i % 5 == 0) {
                blogRedisService.incrementBlogScore("5")
            }
            if (i % 6 == 0) {
                blogRedisService.incrementBlogScore("6")
            }
            if (i % 7 == 0) {
                blogRedisService.incrementBlogScore("7")
            }
            if (i % 8 == 0) {
                blogRedisService.incrementBlogScore("8")
            }
            if (i % 9 == 0) {
                blogRedisService.incrementBlogScore("9")
            }
            if (i % 10 == 0) {
                blogRedisService.incrementBlogScore("10")
            }
            if (i % 11 == 0) {
                blogRedisService.incrementBlogScore("11")
            }
            if (i % 12 == 0) {
                blogRedisService.incrementBlogScore("12")
            }
            if (i % 13 == 0) {
                blogRedisService.incrementBlogScore("13")
            }
            if (i % 14 == 0) {
                blogRedisService.incrementBlogScore("14")
            }
            if (i % 15 == 0) {
                blogRedisService.incrementBlogScore("15")
            }
            if (i % 16 == 0) {
                blogRedisService.incrementBlogScore("16")
            }
        }
        val pupularBlogs = blogRedisService.getPupularBlogs()

        log.info("pupularBlogs {}", pupularBlogs)
    }


    @DisplayName("동시성 테스트")
    @Test
    fun incrementConcurrencyBlogScore()  {
        // 동시에 실행할 작업 개수
        val concurrentJobs = 100

        // 각 작업을 수행하는 코루틴 리스트
        val jobs = List(concurrentJobs) {
            thread {
                // 여기에서 동시성 작업 수행 (예: 데이터 읽기/쓰기, 연산 등)
                blogRedisService.incrementBlogScore(query)
                println("Job $it completed")
            }
        }

        // 모든 작업이 완료될 때까지 대기
        jobs.forEach { it.join() }

        val blogScore = blogRedisService.getBlogScore(query)
        log.info("blogScore {}", blogScore)
    }
}