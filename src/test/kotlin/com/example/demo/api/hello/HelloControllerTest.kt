package com.example.demo.api.hello

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WithMockUser
@WebMvcTest(HelloController::class)
class HelloControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun saysHelloWorld() {
        mockMvc.perform(get("/hello")).andExpect(status().isOk)
            .andExpect(content().json("""{ "greeting": "Hello, World!" }"""))
    }

    @Test
    fun saysHelloFoo() {
        mockMvc.perform(get("/hello?name=Foo")).andExpect(status().isOk)
            .andExpect(content().json("""{ "greeting": "Hello, Foo!" }"""))
    }
}
