package com.motenji.utility

data class ResponseWrapper <T> (
    val success: Boolean,
    val data: T? = null,
    val message: String? = null,
    val errorCode: String? = null
)