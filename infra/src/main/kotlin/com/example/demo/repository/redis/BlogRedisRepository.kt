package com.example.demo.repository.redis

import com.example.demo.entity.BlogCounter
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRedisRepository : CrudRepository<BlogCounter, String> {
}