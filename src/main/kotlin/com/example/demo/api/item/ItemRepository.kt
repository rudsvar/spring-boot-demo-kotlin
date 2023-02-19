package com.example.demo.api.item

import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<ItemEntity, Long> {
    fun existsByName(name: String): Boolean
}