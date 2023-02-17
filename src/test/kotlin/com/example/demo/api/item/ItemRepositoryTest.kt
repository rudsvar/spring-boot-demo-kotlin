package com.example.demo.api.item

import com.example.demo.DemoApplication
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import java.util.*

@DataJpaTest
@ContextConfiguration(classes = [DemoApplication::class])
class RepositoriesTests @Autowired constructor(
    val itemRepository: ItemRepository
) {

    @Test
    fun createThenGetItem() {
        val item = Item("name", null)
        val saved = itemRepository.save(item)
        assertEquals(item, saved)

        val found = itemRepository.findById(saved.id ?: 0)
        assertEquals(Optional.of(saved), found)
    }
}