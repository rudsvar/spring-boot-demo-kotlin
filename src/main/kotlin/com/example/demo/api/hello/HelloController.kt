package com.example.demo.api.hello

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(@RequestParam("name") name: String?): String {
        return String.format("Hello, %s!", name ?: "World")
    }
}