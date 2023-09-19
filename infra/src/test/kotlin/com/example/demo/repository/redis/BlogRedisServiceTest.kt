package com.example.demo.repository.redis

import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.servlet.function.ServerResponse.async

@SpringBootTest
@OptIn(ExperimentalCoroutinesApi::class)
class BlogRedisServiceTest
{
    @Autowired
    lateinit var blogRedisService: BlogRedisService

    var log = LoggerFactory.getLogger(this.javaClass)
    val sharedScheduler = TestCoroutineDispatcher()

   @Test
    fun incrementBlogCounter() {
        var incrementBlogCounter = blogRedisService.incrementBlogCounter("김밥")

        log.info("incrementBlogCounter {}", incrementBlogCounter)
        incrementBlogCounter = blogRedisService.incrementBlogCounter("김밥")
        log.info("incrementBlogCounter {}", incrementBlogCounter)

        incrementBlogCounter = blogRedisService.incrementBlogCounter("김밥")
        log.info("incrementBlogCounter {}", incrementBlogCounter)

        incrementBlogCounter = blogRedisService.incrementBlogCounter("김밥")
        log.info("incrementBlogCounter {}", incrementBlogCounter)
    }

    @Test
    fun incrementConcurrencyBlogCounter()  = sharedScheduler.runBlockingTest {

        // 동시에 실행할 작업 개수
        val concurrentJobs = 100

        // 각 작업을 수행하는 코루틴 리스트
        val jobs = List(concurrentJobs) {
            async(sharedScheduler) {
                // 여기에서 동시성 작업 수행 (예: 데이터 읽기/쓰기, 연산 등)
                blogRedisService.incrementBlogCounter("김밥")
                println("Job $it completed")
            }
        }

        // 모든 작업이 완료될 때까지 대기
        jobs.forEach { it.join() }

        // 테스트가 끝날 때 디스패처를 종료
        sharedScheduler.cleanupTestCoroutines()
        val blogCounter = blogRedisService.getBlogCounter("김밥")
        log.info("blogCounter {}", blogCounter)
    }
}