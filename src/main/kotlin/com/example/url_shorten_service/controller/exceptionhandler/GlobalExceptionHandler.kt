package com.example.url_shorten_service.controller.exceptionhandler

import com.example.url_shorten_service.controller.jsons.ErrorJson
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

class NotFoundException(message: String) : RuntimeException(message)

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ErrorJson> {
        val errorJson = ErrorJson(
            status = HttpStatus.NOT_FOUND.value(),
            message = exception.message ?: "Not found"
        )
        return ResponseEntity(errorJson, HttpStatus.NOT_FOUND)
    }
}