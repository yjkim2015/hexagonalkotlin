package com.example.demo.domain.blog

import com.example.demo.port.`in`.BlogDomainService
import org.springframework.stereotype.Service

@Service
class BlogDomainServiceImpl: BlogDomainService {
    override suspend fun getBlogSearch(keyword: String?, sort: String, page: Int, size: Int): Object? {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularBlog(): Object? {
        TODO("Not yet implemented")
    }
}