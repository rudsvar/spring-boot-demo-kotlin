package com.example.demo.infra

import com.example.demo.infra.exception.ApiException
import com.example.demo.infra.exception.ConflictException
import com.example.demo.infra.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class RestExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(value = [Exception::class])
    private fun fallback(ex: Exception, request: WebRequest): ResponseEntity<ErrorBody> {
        log.warn("Caught internal error: ${ex.message}")
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        val body = ErrorBody(status, "Internal server error")
        return ResponseEntity.status(status).body(body)
    }

    @ExceptionHandler(value = [ApiException::class])
    private fun apiExceptionHandler(ex: ApiException): ResponseEntity<ErrorBody> {
        log.warn("Caught API error: ${ex.message}")
        val status = when (ex) {
            is ConflictException -> HttpStatus.CONFLICT
            is NotFoundException -> HttpStatus.NOT_FOUND
        };
        val body = ErrorBody(status, ex.message)
        return ResponseEntity.status(status).body(body)
    }
}