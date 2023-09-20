package com.example.demo.port.out

import com.example.demo.model.BlogDto
import com.example.demo.model.BlogRankingDto
import com.example.demo.model.KakaoBlogDto

interface BlogGateway {
    suspend fun getKakaoBlogSearch(query: String, sort: String, page: Int, size: Int): BlogDto?
    suspend fun getNaverBlogSearch(query: String, display: Int, start: Int, sort: String): BlogDto?
    suspend fun incrementBlogCount(query: String)
    suspend fun getPopularBlog(): List<BlogRankingDto>?
}