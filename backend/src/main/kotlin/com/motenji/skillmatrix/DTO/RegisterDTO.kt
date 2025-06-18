package com.motenji.skillmatrix.DTO

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

data class RegisterDTO (
    @field:NotBlank(message = "Please enter your username")
    @field:Length(min = 3, max = 15, message = "Name must be between 3 and 15 characters")
    val username: String,
    @field:NotBlank(message = "Please enter your password")
    @field:Length(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    val password: String,
    @field:NotBlank(message = "Please confirm your password")
    val passwordConfirm: String
)