package com.example.demo.port.`in`

import com.example.demo.model.BlogDto
import com.example.demo.model.BlogRankingDto

interface BlogDomainService {
    suspend fun getBlogSearch(query: String, sort: String, page: Int, size: Int): BlogDto?

    suspend fun getPopularBlog(): List<BlogRankingDto>?
}