package com.example.url_shorten_service.repository

import com.example.url_shorten_service.model.UrlMapping
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlMappingRepository : CrudRepository<UrlMapping, Long> {

    fun findByShortUrl(shortUrl: String): UrlMapping?

    fun findByLongUrl(longUrl: String): UrlMapping?

}