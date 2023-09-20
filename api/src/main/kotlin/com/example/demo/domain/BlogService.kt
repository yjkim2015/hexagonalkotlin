package com.example.demo.domain

import com.example.demo.model.BlogDto
import com.example.demo.model.BlogRankingDto
import com.example.demo.port.`in`.BlogDomainService
import lombok.RequiredArgsConstructor
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service

@Service
@Slf4j
@RequiredArgsConstructor
class BlogService(
    private val blogDomainService: BlogDomainService
) {
    suspend fun getBlogSearch(query: String, sort: String, page: Int, size: Int) : BlogDto? {
        return blogDomainService.getBlogSearch(query, sort, page, size)
    }

    suspend fun getPopularBlog(): List<BlogRankingDto>? {
        return blogDomainService.getPopularBlog()
    }
}