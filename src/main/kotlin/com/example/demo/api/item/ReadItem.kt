package com.example.demo.api.item

import java.time.OffsetDateTime

data class ReadItem(
    val id: Long,
    val name: String,
    val description: String?,
    val createdDate: OffsetDateTime?,
    val lastModifiedDate: OffsetDateTime?
)