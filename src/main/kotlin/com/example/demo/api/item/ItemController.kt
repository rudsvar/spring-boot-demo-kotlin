package com.example.demo.api.item

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(@Autowired private val itemService: ItemService) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    fun createItem(@RequestBody createItem: CreateItem): ReadItem {
        log.info("Creating item {}", createItem)
        return itemService.createItem(createItem)
    }

    @GetMapping("/items/{id}")
    fun readItem(@PathVariable id: Long): ReadItem {
        log.info("Reading item {}", id)
        return itemService.readItem(id)
    }

    @PutMapping("/items/{id}")
    fun updateItem(@PathVariable id: Long, @RequestBody updateItem: UpdateItem): ReadItem {
        log.info("Updating item {} to {}", id, updateItem)
        return itemService.updateItem(id, updateItem)
    }

    @DeleteMapping("/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteItem(@PathVariable("id") id: Long) {
        log.info("Deleting item")
        return itemService.deleteItem(id)
    }

    @GetMapping("/items")
    fun listItems(
        @RequestParam id: Long?,
        @RequestParam name: String?,
        @RequestParam description: String?
    ): Iterable<ReadItem> {
        val search = SearchItem(id, name, description)
        log.info("Listing items matching {}", search)
        return itemService.searchItems(search)
    }

    @PostMapping("/items/search")
    fun searchItems(
        @RequestBody search: SearchItem
    ): Iterable<ReadItem> {
        log.info("Searching for items matching {}", search)
        return itemService.searchItems(search)
    }
}