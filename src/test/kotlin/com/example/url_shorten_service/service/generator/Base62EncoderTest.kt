package com.example.url_shorten_service.service.generator

import kotlin.test.Test
import kotlin.test.assertEquals

class Base62EncoderTest {

    private val sut: Base62Encoder = Base62Encoder()

    @Test
    fun `should encode a long number to base62 string`() {
        // Given
        val number = 1932403L
        // When
        val result = sut.encode(number)
        // Then
        assertEquals("86hn", result)
    }

    @Test
    fun `should encode 0 to base62 string`() {
        // Given
        val number = 0L
        // When
        val result = sut.encode(number)
        // Then
        assertEquals("0", result)
    }

    @Test
    fun `should encode max long number to base62 string`() {
        // Given
        val number = Long.MAX_VALUE
        // When
        val result = sut.encode(number)
        // Then
        assertEquals("AzL8n0Y58m7", result)
    }

}