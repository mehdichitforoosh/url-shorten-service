package com.example.url_shorten_service.service

import com.example.url_shorten_service.dto.CreateShortUrlDto
import com.example.url_shorten_service.dto.UrlMappingDto

interface UrlMappingService {

    fun createUrlMapping(dto: CreateShortUrlDto): UrlMappingDto

    fun getUrlMapping(shortUrl: String): UrlMappingDto?
}