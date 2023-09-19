package com.example.demo.controller

import com.example.demo.domain.BlogService
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
    suspend fun getBlogSearch(@RequestParam("query") query:String?, @RequestParam("sort") sort:String = "accuracy",
    @RequestParam("page") page: Int = 1, @RequestParam("size") size: Int = 10): Object?{        return blogService.getBlogSearch(query, sort, page, size);
    }

    @RequestMapping(value=["/popularity"], produces = ["application/json"], method = [RequestMethod.GET])
    suspend fun getPopularBlog(): Object? {
        return blogService.getPopularBlog();
    }
}