package com.example.url_shorten_service.service.generator

import org.springframework.stereotype.Service

@Service
class Base62Encoder {

    private val toBase62 = charArrayOf(
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
        'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    )

    fun encode(number: Long): String {
        var num = number
        val base62 = StringBuilder()
        if (num == 0L) {
            base62.append(toBase62[0])
        }
        while (num > 0) {
            val mod = (num % 62).toInt()
            base62.append(toBase62[mod])
            num /= 62
        }
        return base62.reverse().toString()
    }
}