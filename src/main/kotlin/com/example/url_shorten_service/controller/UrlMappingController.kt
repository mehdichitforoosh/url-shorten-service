package com.example.url_shorten_service.controller

import com.example.url_shorten_service.controller.exceptionhandler.NotFoundException
import com.example.url_shorten_service.controller.jsons.CreateShortUrlJson
import com.example.url_shorten_service.controller.jsons.UrlMappingJson
import com.example.url_shorten_service.dto.CreateShortUrlDto
import com.example.url_shorten_service.service.UrlMappingService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/short-urls")
class UrlMappingController(private var urlMappingService: UrlMappingService) {

    @PostMapping
    fun createUrlMapping(@RequestBody json: CreateShortUrlJson): UrlMappingJson {
        val createShortUrlDto = CreateShortUrlDto(json.longUrl)
        val shortUrlDto = urlMappingService.createUrlMapping(createShortUrlDto)
        return UrlMappingJson(shortUrlDto.id, shortUrlDto.shortUrl)
    }

    @GetMapping("/{shortUrl}")
    fun getUrlMapping(@PathVariable shortUrl: String): UrlMappingJson? {
        val shortUrlDto = urlMappingService.getUrlMapping(shortUrl)
        return shortUrlDto?.let { UrlMappingJson(shortUrlDto.id, shortUrlDto.shortUrl, shortUrlDto.longUrl) }
            ?: throw NotFoundException("Short url $shortUrl not found")
    }
}