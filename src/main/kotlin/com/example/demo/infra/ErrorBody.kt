package com.example.demo.infra

import org.springframework.http.HttpStatus
import java.time.OffsetDateTime

class ErrorBody(status: HttpStatus, val message: String) {
    val timestamp: OffsetDateTime = OffsetDateTime.now()
    val status: Int = status.value()
}