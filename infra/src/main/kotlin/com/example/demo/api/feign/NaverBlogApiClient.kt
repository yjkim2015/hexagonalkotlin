package com.example.demo.api.feign

import com.example.demo.entity.NaverBlogEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono


@ReactiveFeignClient(
    name = "naverBlogApi",
    url = "\${apis.naver.url}"
)
interface NaverBlogApiClient {
    @GetMapping("/v1/search/blog.json")
    fun getNaverBlogSearch(
        @RequestHeader("X-Naver-Client-Id") clientId: String,
        @RequestHeader("X-Naver-Client-Secret") clientSecret: String,
        @RequestParam("query") query: String,
        @RequestParam("display") display: Int,
        @RequestParam("start") start: Int,
        @RequestParam("sort") sort: String): Mono<NaverBlogEntity>
}