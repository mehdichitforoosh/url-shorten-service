package com.example.url_shorten_service.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "url_mapping")
data class UrlMapping(
   @Id val id: Long, @Column(name = "short_url") val shortUrl: String, @Column(name = "long_url") val longUrl: String
)