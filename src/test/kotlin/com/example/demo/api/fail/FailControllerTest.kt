package com.example.demo.api.fail

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WithMockUser
@WebMvcTest(controllers = [FailController::class])
class FailControllerTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun failsWithInternalError() {
        mockMvc.perform(get("/fail")).andExpect(status().isInternalServerError)
            .andExpect(content().json("""{"message": "Internal server error"}"""))
    }
}
