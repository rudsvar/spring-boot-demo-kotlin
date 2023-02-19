package com.example.demo.api.item

class SearchItem(
    private val id: Long?,
    val name: String?,
    private val description: String?,
) {
    fun toEntity(): ItemEntity = ItemEntity(id, name, description)
}
