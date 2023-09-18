package com.example.demo.gateway

import com.example.demo.entity.DocumentEntity
import com.example.demo.entity.MetaEntity
import com.example.demo.model.DocumentDto
import com.example.demo.model.KakaoBlogDto
import com.example.demo.model.MetaDto
import com.example.demo.port.out.BlogGateway
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class BlogGatewayImpl: BlogGateway {

    /*@Autowired
    lateinit var kakaoBlogApiClient: KakaoBlogApiClient*/

    override suspend fun getKakaoBlogSearch(query: String?, sort: String, page: Int, size: Int): KakaoBlogDto {
        TODO("Not yet implemented")
        /*return kakaoBlogApiClient.getKakaoBlogSearch("KakaoAK", query, sort, page, size).awaitFirst().let {
            KakaoBlogDto(convertMetaEntityToDto(it.meta), convertDocumentsEntityToDto(it.documents))
        }*/
    }

    override suspend fun getNaverBlogSearch() {
        TODO("Not yet implemented")
    }

    override suspend fun getPopularBlog() {
        TODO("Not yet implemented")
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