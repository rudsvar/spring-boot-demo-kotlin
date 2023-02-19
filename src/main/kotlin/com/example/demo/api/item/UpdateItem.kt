package com.example.demo.api.item

data class UpdateItem(
    val name: String,
    val description: String?,
)

fun UpdateItem.toEntity(id: Long) = ItemEntity(id, name, description)