package com.example.demo.api.item

import com.example.demo.DemoApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import java.util.*

@DataJpaTest
@ContextConfiguration(classes = [DemoApplication::class])
class ItemRepositoryTests @Autowired constructor(
    val itemRepository: ItemRepository
) {

    @Test
    fun createThenGetItem() {
        val newItem = NewItem("name", null)
        val saved = itemRepository.save(newItem.toEntity())
        assertNotNull(saved.id)
        assertEquals(newItem.name, saved.name)
        assertEquals(newItem.description, saved.description)

        val found = itemRepository.findById(saved.id!!)
        assertEquals(Optional.of(saved), found)
    }
}