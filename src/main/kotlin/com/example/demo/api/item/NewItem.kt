package com.example.demo.api.item

data class NewItem(
    val name: String,
    val description: String?,
)

fun NewItem.toEntity() = ItemEntity(null, name, description)
