package com.example.url_shorten_service.service

import com.example.url_shorten_service.dto.CreateShortUrlDto
import com.example.url_shorten_service.dto.UrlMappingDto
import com.example.url_shorten_service.model.UrlMapping
import com.example.url_shorten_service.repository.UrlMappingRepository
import com.example.url_shorten_service.service.generator.Base62Encoder
import com.example.url_shorten_service.service.generator.UniqueIdGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DefaultUrlMappingService(
    private var urlMappingRepository: UrlMappingRepository,
    private var uniqueIdGenerator: UniqueIdGenerator,
    private var base62Encoder: Base62Encoder
) : UrlMappingService {

    @Transactional
    override fun createUrlMapping(dto: CreateShortUrlDto): UrlMappingDto {
        val existing = urlMappingRepository.findByLongUrl(dto.longUrl)
        if(existing != null){
            return UrlMappingDto(existing.id, existing.shortUrl)
        }
        val id = uniqueIdGenerator.generateId()
        val shortUrl = base62Encoder.encode(id)
        urlMappingRepository.save(UrlMapping(id, shortUrl, dto.longUrl))
        return UrlMappingDto(id, shortUrl)
    }

    @Transactional(readOnly = true)
    override fun getUrlMapping(shortUrl: String): UrlMappingDto? {
        val urlMapping = urlMappingRepository.findByShortUrl(shortUrl)
        return urlMapping?.let { UrlMappingDto(urlMapping.id, urlMapping.shortUrl, urlMapping.longUrl) }
    }
}