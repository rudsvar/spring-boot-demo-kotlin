package com.example.demo.api.fail

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FailController {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/fail")
    fun fail(): String {
        log.info("Going to fail")
        throw RuntimeException("Oh no!")
    }
}