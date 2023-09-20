package com.example.demo.domain.blog

import com.example.demo.model.BlogDto
import com.example.demo.port.`in`.BlogDomainService
import com.example.demo.port.out.BlogGateway
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BlogDomainServiceImpl(
    val blogGateway: BlogGateway
): BlogDomainService {
    @Transactional
    override suspend fun getBlogSearch(query: String, sort: String, page: Int, size: Int): BlogDto? {
        var blogSearch = blogGateway.getKakaoBlogSearch(query, sort, page, size)
        if (blogSearch == null) {
            blogSearch = blogGateway.getNaverBlogSearch(query, size, page, sort)
        }
        blogGateway.incrementBlogCount(query)
        return blogSearch
    }
    override suspend fun getPopularBlog(): Set<String>? {
        return blogGateway.getPopularBlog()
    }
}