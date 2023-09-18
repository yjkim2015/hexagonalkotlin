package com.example.demo.entity

data class KakaoBlogEntity(val meta: MetaEntity, val documents: List<DocumentEntity>)


data class MetaEntity(val total_count: Int, val pageable_count: Int, val is_end: Boolean)


data class DocumentEntity(val datetime: String, val contents: String, val title: String, val url: String)

