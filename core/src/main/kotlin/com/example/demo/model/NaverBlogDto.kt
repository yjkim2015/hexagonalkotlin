package com.example.demo.model

class NaverBlogDto(
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<NaverBlogItemDto>
) : BlogDto()

class NaverBlogItemDto(
    val title: String,
    val link: String,
    val description: String,
    val bloggername: String,
    val bloggerlink: String,
    val postdate: String
)