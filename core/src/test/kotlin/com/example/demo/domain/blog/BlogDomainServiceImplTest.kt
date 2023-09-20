package com.example.demo.domain.blog

import com.example.demo.model.BlogDto
import com.example.demo.port.out.BlogGateway
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class BlogDomainServiceImplTest {

    @Mock
    lateinit var blogGateway: BlogGateway

    @InjectMocks lateinit var blogDomainServiceImpl: BlogDomainServiceImpl

    var query: String = "김밥"
    var page: Int = 1
    var size: Int = 10
    var sort = "accuracy"


    @Test
    fun getBlogSearchTest() = runBlocking {
        org.mockito.Mockito.`when`(
            blogGateway.getKakaoBlogSearch(query, sort, page, size))
            .thenReturn(BlogDto())

        val blogSearch = blogDomainServiceImpl.getBlogSearch(query, sort, page, size)
        assertNotNull(blogSearch)
    }

    @DisplayName("카카오 장애시 네이버")
    @Test
    fun getBlogSearchKakaoErrorTest() = runBlocking {
        org.mockito.Mockito.`when`(
            blogGateway.getKakaoBlogSearch(query, sort, page, size))
            .thenReturn(null)

        org.mockito.Mockito.`when`(
            blogGateway.getNaverBlogSearch(query, size, page, sort))
            .thenReturn(BlogDto())

        val blogSearch = blogDomainServiceImpl.getBlogSearch(query, sort, page, size)
        assertNotNull(blogSearch)
    }
}