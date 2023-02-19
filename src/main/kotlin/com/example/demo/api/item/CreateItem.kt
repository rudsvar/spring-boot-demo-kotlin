package com.example.demo.api.item

data class CreateItem(
    val name: String,
    val description: String?,
)

fun CreateItem.toEntity() = ItemEntity(null, name, description)
