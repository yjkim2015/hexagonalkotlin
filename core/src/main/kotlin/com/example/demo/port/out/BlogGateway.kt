package com.example.demo.port.out

import com.example.demo.model.KakaoBlogDto

interface BlogGateway {

    suspend fun getKakaoBlogSearch(query: String?, sort: String, page: Int, size: Int): KakaoBlogDto

    suspend fun getNaverBlogSearch()

    suspend fun getPopularBlog();
}