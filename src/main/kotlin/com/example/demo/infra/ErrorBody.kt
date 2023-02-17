package com.example.demo.infra

import java.time.OffsetDateTime

class ErrorBody(val message: String) {
    val timestamp: OffsetDateTime = OffsetDateTime.now()
}