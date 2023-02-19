package com.example.demo.api.item

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "items")
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    var name: String?,
    var description: String?,
) {
    fun toReadItem(): ReadItem {
        // Name and id are not really nullable, ok to use !!
        return ReadItem(name = this.name!!, description = this.description, id = this.id!!)
    }
}
