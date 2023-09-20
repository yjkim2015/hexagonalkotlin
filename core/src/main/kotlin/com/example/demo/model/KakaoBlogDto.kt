package com.example.demo.model

class KakaoBlogDto(
    val meta: MetaDto,
    val documents: List<DocumentDto>
) : BlogDto()

class MetaDto(val total_count: Int, val pageable_count: Int, val is_end: Boolean)

class DocumentDto(val datetime: String, val contents: String, val title: String, val url: String)

