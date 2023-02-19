package com.example.demo.api.item

import com.example.demo.infra.exception.ConflictException
import com.example.demo.infra.exception.NotFoundException
import jakarta.transaction.Transactional
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@Transactional
class ItemController(@Autowired val itemRepository: ItemRepository) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    fun createItem(@RequestBody newItem: NewItem): ReadItem {
        log.info("Creating item {}", newItem)

        // Check if name is already used
        if (itemRepository.existsByName(newItem.name)) {
            throw ConflictException("Item with that name already exists")
        }

        return itemRepository.save(newItem.toEntity()).toReadItem()
    }

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    fun readItem(id: Long): ReadItem {
        log.info("Reading item {}", id)
        return itemRepository.findById(id).map { it.toReadItem() }
            .orElseThrow { NotFoundException("Item not found") }
    }

    @PutMapping("/items/{id}")
    fun updateItem(@PathVariable("id") id: Long, @RequestBody updateItem: UpdateItem): ReadItem {
        log.info("Updating item {} to {}", id, updateItem)

        // Check if item exists
        if (!itemRepository.existsById(id)) {
            throw NotFoundException("Item not found")
        }

        return itemRepository.save(updateItem.toEntity(id)).toReadItem()
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteItem(@PathVariable("id") id: Long) {
        log.info("Deleting item")

        // Check if item exists
        if (!itemRepository.existsById(id)) {
            throw NotFoundException("Item not found")
        }

        itemRepository.deleteById(id)
    }

    @GetMapping("/items")
    fun listItems(
        @RequestParam id: Long?,
        @RequestParam name: String?,
        @RequestParam description: String?
    ): Iterable<ReadItem> {
        val search = SearchItem(id, name, description)
        log.info("Searching for item matching {}", search)
        val items = itemRepository.findAll(Example.of(search.toEntity()))
        return items.map { it.toReadItem() }
    }
}