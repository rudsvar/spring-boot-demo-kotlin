package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@WebMvcTest(HelloController::class)
class HelloControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun saysHelloWorld() {
        mockMvc.perform(get("/hello")).andExpect(content().string("Hello, World!"))
    }

    @Test
    fun saysHelloFoo() {
        mockMvc.perform(get("/hello?name=Foo")).andExpect(content().string("Hello, Foo!"))
    }
}
