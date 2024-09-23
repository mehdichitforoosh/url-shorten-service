package com.example.url_shorten_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlShortenServiceApplication

fun main(args: Array<String>) {
	runApplication<UrlShortenServiceApplication>(*args)
}
