package com.example.url_shorten_service.controller.jsons

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UrlMappingJson(val id: Long, val shortUrl: String, val longUrl: String? = null)