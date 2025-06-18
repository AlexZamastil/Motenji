package com.motenji.skillmatrix.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class GoalCreateDTO (
    @field:NotBlank(message = "Goal name cannot be empty")
    @field:Size(min = 3, max = 100, message = "Name must be between 1 and 100 characters")
    val name: String,
    val measurable: Boolean,
    @field:NotBlank(message = "Please set a goal deadline")
    val deadline: LocalDate,
    val progress: Double = 0.0,
    @field:NotNull(message = "User info missing")
    val userId: Long
)