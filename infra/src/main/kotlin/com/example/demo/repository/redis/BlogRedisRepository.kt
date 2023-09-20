package com.example.demo.repository.redis

import com.example.demo.entity.BlogEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRedisRepository : CrudRepository<BlogEntity, String> {
}