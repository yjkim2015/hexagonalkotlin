package com.example.demo.gateway

import com.example.demo.api.feign.KakaoBlogApiClient
import com.example.demo.api.feign.NaverBlogApiClient
import com.example.demo.entity.BlogEntity
import com.example.demo.entity.DocumentEntity
import com.example.demo.entity.MetaEntity
import com.example.demo.entity.NaverBlogItemEntity
import com.example.demo.model.*
import com.example.demo.port.out.BlogGateway
import com.example.demo.repository.redis.BlogRedisService
import kotlinx.coroutines.reactive.awaitFirst
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Slf4j
class BlogGatewayImpl: BlogGateway {
    var log = LoggerFactory.getLogger(this.javaClass)

    @Value("\${apis.kakao.auth}")
    private lateinit var kakaoAuth: String


    @Value("\${apis.naver.client_id}")
    private lateinit var clientId: String

    @Value("\${apis.naver.client_secret}")
    private lateinit var client_secret: String

    @Autowired
    lateinit var kakaoBlogApiClient: KakaoBlogApiClient

    @Autowired
    lateinit var naverBlogApiClient: NaverBlogApiClient

    @Autowired
    lateinit var blogRedisService: BlogRedisService

    override suspend fun getKakaoBlogSearch(query: String, sort: String, page: Int, size: Int): BlogDto? {
        return null
        log.info("getKakaoBlogSearch")
        try {
            var key = String.format("kakaoBlog {%s}:{%s}:{%s}:{%s}", query, sort, page, size)
            var blog = blogRedisService.getBlog(key)

            if (blog == null){
                val blogDto = kakaoBlogApiClient.getKakaoBlogSearch(kakaoAuth, query, sort, page, size).awaitFirst().let {
                    log.info("noKakaoCache")

                    KakaoBlogDto(convertMetaEntityToDto(it.meta), convertDocumentsEntityToDto(it.documents))
                }
                blogRedisService.saveBlog(BlogEntity(key, blogDto))
                return blogDto
            }
            return blog.value as BlogDto
        }
        catch (ex: Exception) {
            log.error(ex.message)
            return null
        }
    }

    override suspend fun getNaverBlogSearch(query: String, display: Int, start: Int, sort: String): BlogDto? {
        log.info("getNaverBlogSearch")
        try {
            var naverSort = "sim";
            if (sort != "accuracy") {
                naverSort = "date"
            }
            var key = String.format("naverBlog {%s}:{%s}:{%s}:{%s}", query, display, start, naverSort)
            var blog = blogRedisService.getBlog(key)

            if (blog == null){
                val blogDto = naverBlogApiClient.getNaverBlogSearch(clientId, client_secret, query, display, start, naverSort)
                    .awaitFirst().let {
                    log.info("noNaverCache")
                    NaverBlogDto(it.total, it.start, it.display, convertItemsEntityToDto(it.items))
                }
                blogRedisService.saveBlog(BlogEntity(key, blogDto))
                return blogDto
            }
            return blog.value as BlogDto
        }
        catch (ex : Exception) {
            log.error(ex.message)
            return null
        }
    }

    override suspend fun incrementBlogCount(query: String) {
       blogRedisService.incrementBlogScore(query)
    }

    override suspend fun getPopularBlog() : List<BlogRankingDto>? {
        return blogRedisService.getPupularBlogs()
    }

    private fun convertItemsEntityToDto(items: List<NaverBlogItemEntity>): List<NaverBlogItemDto> {
        var result = ArrayList<NaverBlogItemDto>()

        for (item in items) {
            result.add(NaverBlogItemDto(item.title, item.link, item.description, item.bloggername, item.bloggerlink, item.postdate))
        }
        return result
    }


    private fun convertDocumentsEntityToDto(documentEntities: List<DocumentEntity>): List<DocumentDto> {
        var result = ArrayList<DocumentDto>()

        for(documentEntity in documentEntities) {
            result.add(DocumentDto(documentEntity.datetime, documentEntity.contents, documentEntity.title, documentEntity.url))
        }

        return result
    }

    private fun convertMetaEntityToDto(metaEntity: MetaEntity): MetaDto {
        return MetaDto(metaEntity.total_count, metaEntity.pageable_count, metaEntity.is_end)
    }
}