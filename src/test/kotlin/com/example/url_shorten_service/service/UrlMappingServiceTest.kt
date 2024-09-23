package com.example.url_shorten_service.service

import com.example.url_shorten_service.dto.CreateShortUrlDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest
class UrlMappingServiceTest {

    @Autowired
    private lateinit var sut: UrlMappingService

    @Test
    fun `should create unique short url`() {
        // Given
        val dto = CreateShortUrlDto("https://www.example.com")
        // When
        val result = this.sut.createUrlMapping(dto)
        // Then
        assertNotNull(result)
        assertTrue(result.shortUrl.isNotEmpty())
    }

    @Test
    fun `should return the same short url when it exists`() {
        // Given
        val dto = CreateShortUrlDto("https://www.example.com")
        val urlMappingDto = this.sut.createUrlMapping(dto)
        // When
        val result = this.sut.createUrlMapping(dto)
        // Then
        assertNotNull(result)
        assertEquals(urlMappingDto.shortUrl, result.shortUrl)
    }

    @Test
    fun `should return the long url`() {
        // Given
        val dto = CreateShortUrlDto("https://www.example.com")
        val urlMappingDto = this.sut.createUrlMapping(dto)
        // When
        val result = this.sut.getUrlMapping(urlMappingDto.shortUrl)
        // Then
        assertNotNull(result)
        assertEquals("https://www.example.com", result.longUrl)
    }
}