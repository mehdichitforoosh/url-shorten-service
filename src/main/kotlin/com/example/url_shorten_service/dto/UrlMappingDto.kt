package com.example.url_shorten_service.dto

data class UrlMappingDto(val id: Long, val shortUrl: String, val longUrl: String? = null)