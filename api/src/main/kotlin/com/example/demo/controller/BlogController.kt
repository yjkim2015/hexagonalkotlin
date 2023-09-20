package com.example.demo.controller

import com.example.demo.domain.BlogService
import com.example.demo.model.BlogDto
import com.example.demo.model.BlogRankingDto
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
class BlogController(
    private val blogService: BlogService
) {
    @RequestMapping(value=["/search"], produces = ["application/json"], method = [RequestMethod.GET])
    suspend fun getBlogSearch(@RequestParam("query") query:String, @RequestParam("sort") sort:String,
    @RequestParam("page") page: Int, @RequestParam("size") size: Int): BlogDto?{
        return blogService.getBlogSearch(query, sort, page, size);
    }

    @RequestMapping(value=["/popularity"], produces = ["application/json"], method = [RequestMethod.GET])
    suspend fun getPopularBlog(): List<BlogRankingDto>? {
        return blogService.getPopularBlog();
    }
}