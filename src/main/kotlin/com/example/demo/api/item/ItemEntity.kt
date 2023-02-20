package com.example.demo.api.item

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.OffsetDateTime

@Entity(name = "items")
@EntityListeners(
    AuditingEntityListener::class
)
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "name")
    var name: String?,
    @Column(name = "description")
    var description: String?,
) {
    @CreatedDate
    @Column(name = "createdDate", updatable = false)
    var createdDate: OffsetDateTime? = null

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    var lastModifiedDate: OffsetDateTime? = null

    fun toReadItem(): ReadItem {
        // Name and id are not really nullable, ok to use !!
        return ReadItem(
            name = this.name!!,
            description = this.description,
            id = this.id!!,
            createdDate = this.createdDate,
            lastModifiedDate = this.lastModifiedDate
        )
    }
}
