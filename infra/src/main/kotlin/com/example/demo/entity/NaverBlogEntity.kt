package com.example.demo.entity

class NaverBlogEntity(val total: Int, val start: Int, val display: Int, val items: List<NaverBlogItemEntity>)

class NaverBlogItemEntity(
    val title: String,
    val link: String,
    val description: String,
    val bloggername: String,
    val bloggerlink: String,
    val postdate: String
)