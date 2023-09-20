package com.example.demo.api.feign

import com.example.demo.entity.KakaoBlogEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono
import javax.persistence.Cacheable


@ReactiveFeignClient(
    name = "kakaoBlogApi",
    url = "\${apis.kakao.url}"
)
interface KakaoBlogApiClient {

    @GetMapping("/v2/search/web")
    fun getKakaoBlogSearch(
        @RequestHeader("Authorization") auth: String,
        @RequestParam("query") query: String?,
        @RequestParam("sort") sort: String,
        @RequestParam("page") page: Int,
        @RequestParam("size") size: Int): Mono<KakaoBlogEntity>

}