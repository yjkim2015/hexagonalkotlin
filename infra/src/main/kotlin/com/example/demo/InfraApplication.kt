package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import reactivefeign.spring.config.EnableReactiveFeignClients

@SpringBootApplication
@EnableReactiveFeignClients
class InfraApplication

fun main(args: Array<String>) {
    runApplication<InfraApplication>(*args)
}
