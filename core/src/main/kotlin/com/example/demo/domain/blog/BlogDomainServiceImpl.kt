package com.example.demo.domain.blog

import com.example.demo.exception.CommonErrorCode
import com.example.demo.exception.CustomException
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
        validateSort(sort)
        validatePage(page)
        validateSize(size)
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

    private fun validateSort(sort: String) {
        var result = false
        if (sort == "accuracy") {
            result = true
        }
        else if (sort == "recency") {
            result = true
        }

        if (!result)
            throw CustomException(CommonErrorCode.INVALID_SORT, sort)
    }
    private fun validatePage(page: Int) {
        if (page < 1)
            throw CustomException(CommonErrorCode.INVALID_PAGE, page)
    }

    private fun validateSize(size: Int) {
        if (size < 1)
            throw CustomException(CommonErrorCode.INVALID_SIZE, size)
    }
}