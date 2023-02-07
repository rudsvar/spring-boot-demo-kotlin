package com.example.demo.api.item

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(@Autowired val itemRepository: ItemRepository) {

    @PostMapping("/items")
    fun createItem(@RequestBody item: Item): Item {
        return itemRepository.save(item)
    }

    @GetMapping("/items")
    fun listItems(): Iterable<Item> {
        return itemRepository.findAll()
    }
}