package com.example.demo.port.`in`

import com.example.demo.model.BlogDto

interface BlogDomainService {
    suspend fun getBlogSearch(query: String, sort: String, page: Int, size: Int): BlogDto?

    suspend fun getPopularBlog(): Set<String>?
}