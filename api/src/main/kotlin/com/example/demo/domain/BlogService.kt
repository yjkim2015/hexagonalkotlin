package com.example.demo.domain

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

    suspend fun getBlogSearch(keyword: String?, sort: String, page: Int, size: Int) : Object? {
        return blogDomainService.getBlogSearch(keyword, sort, page, size)
    }

    suspend fun getPopularBlog(): Object? {
        return null
    }
}