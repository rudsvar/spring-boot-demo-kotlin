package com.example.demo.api.item

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*

@WithMockUser
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ItemControllerTest(@Autowired val mockMvc: MockMvc, @Autowired val mapper: ObjectMapper) {

    @Test
    fun createItemWorks() {
        val name = UUID.randomUUID().toString()
        val createItem = mapper.writeValueAsString(CreateItem(name, null))
        mockMvc.perform(post("/items").contentType(MediaType.APPLICATION_JSON).content(createItem))
            .andExpect(status().isCreated)
        mockMvc.perform(post("/items").contentType(MediaType.APPLICATION_JSON).content(createItem))
            .andExpect(status().isConflict)
    }

    @Test
    fun getNonExistingItemGivesNotFound() {
        mockMvc.perform(get("/items/0")).andExpect(status().isNotFound)
    }

    @Test
    fun getExistingItemGivesOk() {
        val name = UUID.randomUUID().toString()
        val createItem = mapper.writeValueAsString(CreateItem(name, null))
        val result = mockMvc.perform(post("/items").contentType(MediaType.APPLICATION_JSON).content(createItem))
            .andReturn().response.contentAsString
        val readItem: ReadItem = mapper.readValue(result, ReadItem::class.java)
        mockMvc.perform(get("/items/" + readItem.id)).andExpect(status().isOk)
    }

    @Test
    fun updateNonExistingItemGivesNotFound() {
        val name = UUID.randomUUID().toString()
        val item = mapper.writeValueAsString(CreateItem(name, null))
        mockMvc.perform(put("/items/0").contentType(MediaType.APPLICATION_JSON).content(item))
            .andExpect(status().isNotFound)
    }

    @Test
    fun updateExistingItemGivesOk() {
        val name = UUID.randomUUID().toString()
        val createItem = mapper.writeValueAsString(CreateItem(name, null))
        val result = mockMvc.perform(post("/items").contentType(MediaType.APPLICATION_JSON).content(createItem))
            .andReturn().response.contentAsString
        val readItem: ReadItem = mapper.readValue(result, ReadItem::class.java)
        mockMvc.perform(get("/items/" + readItem.id)).andExpect(status().isOk)
    }
}
