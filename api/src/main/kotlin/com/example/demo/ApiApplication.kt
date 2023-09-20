package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactivefeign.spring.config.EnableReactiveFeignClients


@EnableReactiveFeignClients
@SpringBootApplication
class ApiApplication
fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
