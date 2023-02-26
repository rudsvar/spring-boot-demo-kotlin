package com.example.demo.infra

import com.example.demo.infra.exception.ApiException
import com.example.demo.infra.exception.ConflictException
import com.example.demo.infra.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class RestExceptionHandler {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ExceptionHandler(value = [Exception::class])
    private fun fallback(ex: Exception, request: WebRequest): ProblemDetail {
        log.warn("Caught internal error: ${ex.message}")
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        return ProblemDetail.forStatusAndDetail(status, "Internal server error")
    }

    @ExceptionHandler(value = [ApiException::class])
    private fun apiExceptionHandler(ex: ApiException): ProblemDetail {
        log.warn("Caught API error: ${ex.message}")
        val status = when (ex) {
            is ConflictException -> HttpStatus.CONFLICT
            is NotFoundException -> HttpStatus.NOT_FOUND
        }
        return ProblemDetail.forStatusAndDetail(status, ex.localizedMessage)
    }
}