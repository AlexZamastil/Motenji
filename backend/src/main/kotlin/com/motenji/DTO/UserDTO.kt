package com.motenji.DTO

import jakarta.validation.constraints.NotBlank


data class UserDTO (
    val id: Long?,
    @field:NotBlank(message = "Please enter nickname")
    val username: String,
    @field:NotBlank(message = "Please enter password")
    val password: String
)