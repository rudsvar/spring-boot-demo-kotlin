package com.example.demo.api.item

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ItemController(@Autowired val itemRepository: ItemRepository) {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/items")
    fun createItem(@RequestBody newItem: NewItem): Item {
        log.info("Creating item")
        val item = Item(newItem.name, newItem.description, null)
        return itemRepository.save(item)
    }

    @GetMapping("/items")
    fun listItems(): Iterable<Item> {
        log.info("Listing items")
        return itemRepository.findAll()
    }
}