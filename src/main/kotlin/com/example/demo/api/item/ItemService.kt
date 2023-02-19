package com.example.demo.api.item

import com.example.demo.infra.exception.ConflictException
import com.example.demo.infra.exception.NotFoundException
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service

@Service
@Transactional
class ItemService(@Autowired private val itemRepository: ItemRepository) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun createItem(createItem: CreateItem): ReadItem {
        log.info("Creating item {}", createItem)

        // Check if name is already used
        if (itemRepository.existsByName(createItem.name)) {
            throw ConflictException("Item with that name already exists")
        }

        return itemRepository.save(createItem.toEntity()).toReadItem()
    }

    fun readItem(id: Long): ReadItem {
        log.info("Reading item {}", id)
        return itemRepository.findById(id).map { it.toReadItem() }
            .orElseThrow { NotFoundException("Item not found") }
    }

    fun updateItem(id: Long, updateItem: UpdateItem): ReadItem {
        log.info("Updating item {} to {}", id, updateItem)

        // Check if item exists
        if (!itemRepository.existsById(id)) {
            throw NotFoundException("Item not found")
        }

        return itemRepository.save(updateItem.toEntity(id)).toReadItem()
    }

    fun deleteItem(id: Long) {
        log.info("Deleting item")

        // Check if item exists
        if (!itemRepository.existsById(id)) {
            throw NotFoundException("Item not found")
        }

        itemRepository.deleteById(id)
    }

    fun searchItems(
        search: SearchItem
    ): Iterable<ReadItem> {
        log.info("Searching for item matching {}", search)
        val items = itemRepository.findAll(Example.of(search.toEntity()))
        return items.map { it.toReadItem() }
    }
}