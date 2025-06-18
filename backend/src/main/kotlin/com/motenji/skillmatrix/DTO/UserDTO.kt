package com.motenji.skillmatrix.DTO

import jakarta.validation.constraints.NotBlank

data class UserDTO (
    @field:NotBlank(message = "User info missing")
    val id: Long?,
    @field:NotBlank(message = "Please enter nickname")
    val username: String,
    @field:NotBlank(message = "Please enter password")
    val password: String
)