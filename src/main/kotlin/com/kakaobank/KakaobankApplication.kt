package com.kakaobank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KakaobankApplication

fun main(args: Array<String>) {
    runApplication<KakaobankApplication>(*args)
}
