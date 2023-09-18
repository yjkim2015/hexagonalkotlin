package com.example.demo.api.feign

import com.example.demo.entity.KakaoBlogEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import reactivefeign.spring.config.ReactiveFeignClient
import reactor.core.publisher.Mono


@ReactiveFeignClient(
    name = "kakaoBlogApi",
    url = "\${apis.url.kakaoapi}"
)
interface KakaoBlogApiClient {

    @GetMapping("/v2/search/web")
    fun getKakaoBlogSearch(@RequestHeader("Authorization") auth: String,
    query: String?, sort: String, page: Int, size: Int): Mono<KakaoBlogEntity>

}