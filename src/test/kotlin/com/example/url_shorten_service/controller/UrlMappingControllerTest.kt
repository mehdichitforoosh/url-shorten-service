package com.example.url_shorten_service.controller

import com.example.url_shorten_service.controller.jsons.CreateShortUrlJson
import com.example.url_shorten_service.dto.CreateShortUrlDto
import com.example.url_shorten_service.service.UrlMappingService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class UrlMappingControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Autowired
    private lateinit var urlMappingService: UrlMappingService

    @Test
    fun `should create short url`() {
        // Given
        val createShortUrlJson = CreateShortUrlJson("https://www.example.com")
        val content = mapper.writeValueAsString(createShortUrlJson)
        // When and Then
        mockMvc.perform(
            post("/api/v1/short-urls")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.shortUrl").isNotEmpty())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber())
    }

    @Test
    fun `should get long url`() {
        // Given
        val dto = CreateShortUrlDto("https://www.example.com")
        val urlMappingDto = urlMappingService.createUrlMapping(dto)
        // When and Then
        mockMvc.perform(
            get("/api/v1/short-urls/{shortUrl}", urlMappingDto.shortUrl)
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.longUrl").value("https://www.example.com"))
    }
}