package com.example.demo.port.`in`

interface BlogDomainService {
    suspend fun getBlogSearch(keyword: String?, sort: String, page: Int, size: Int): Object?

    suspend fun getPopularBlog(): Object?
}