package com.example.demo.api.feign

import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class BlogApiClientTest {

    @Autowired
    lateinit var kakaoBlogApiClient: KakaoBlogApiClient

    @Autowired
    lateinit var naverBlogApiClient: NaverBlogApiClient

    @Value("\${apis.kakao.auth}")
    private lateinit var kakaoAuth: String

    @Value("\${apis.naver.client_id}")
    private lateinit var clientId: String

    @Value("\${apis.naver.client_secret}")
    private lateinit var client_secret: String

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Test
    fun getKakaoBlogSearchTest() {

        val block = kakaoBlogApiClient.getKakaoBlogSearch(kakaoAuth, "김밥", "accuracy", 1, 10).block()
        log.info("{}", block)
    }

    @Test
    fun getNaverBlogSearchTest() {

        val block = naverBlogApiClient.getNaverBlogSearch(clientId, client_secret, "김밥", 10, 1, "sim").block()
        log.info("{}", block)
    }

}