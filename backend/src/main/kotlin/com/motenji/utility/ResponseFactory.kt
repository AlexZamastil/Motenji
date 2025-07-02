package com.motenji.utility

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ResponseFactory {

    fun <T> success(data: T, message: String = "Success"): ResponseEntity<com.motenji.utility.ResponseWrapper<T>>
     = ResponseEntity.ok(com.motenji.utility.ResponseWrapper(success = true, data = data, message = message))

    fun <T> created(data: T, message: String = "Item created"): ResponseEntity<com.motenji.utility.ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.CREATED).body(
        com.motenji.utility.ResponseWrapper(
            success = true,
            data = data,
            message = message
        )
    )

    fun <T> notFound(message: String = "Not found"): ResponseEntity<com.motenji.utility.ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        com.motenji.utility.ResponseWrapper(
            success = false,
            message = message
        )
    )

    fun <T> badRequest( message: String = "Bad request"): ResponseEntity<com.motenji.utility.ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
        com.motenji.utility.ResponseWrapper(
            success = false,
            message = message
        )
    )

    fun <T> internalError(message: String = "Internal server error"): ResponseEntity<com.motenji.utility.ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
        com.motenji.utility.ResponseWrapper(
            success = false,
            message = message
        )
    )
}