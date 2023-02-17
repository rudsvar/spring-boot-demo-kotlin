package com.example.demo.infra

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class RestExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(value = [Exception::class])
    private fun fallback(ex: Exception, request: WebRequest): ResponseEntity<ErrorBody> {
        log.warn("Caught an error: ${ex.message}")
        val body = ErrorBody("Internal server error")
        return ResponseEntity.internalServerError().body(body)
    }
}