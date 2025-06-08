package com.motenji.skillmatrix.utility

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

object ResponseFactory {

    fun <T> success(data: T, message: String = "Success"): ResponseEntity<ResponseWrapper<T>>
     = ResponseEntity.ok(ResponseWrapper(success = true, data = data, message = message))

    fun <T> created(data: T, message: String = "Item created"): ResponseEntity<ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.CREATED).body(ResponseWrapper(success = true, data = data, message = message))

    fun <T> notFound(message: String = "Not found"): ResponseEntity<ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseWrapper(success = false, message = message))

    fun <T> badRequest( message: String = "Bad request"): ResponseEntity<ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper(success = false, message = message))

    fun <T> internalError(message: String = "Internal server error"): ResponseEntity<ResponseWrapper<T>>
            = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseWrapper(success = false, message = message))
}