package com.example.demo.infra.exception

/**
 * A top level exception from our API that can be converted into a response.
 */
sealed class ApiException(override val message: String) : Exception(message)