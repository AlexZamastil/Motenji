package com.motenji.skillmatrix.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class ContributionDTO(
    @field:NotBlank(message = "Goal missing")
    val goalId: Long,
    val name: String,
    val percentageIncrement: Int?,
    @field:NotNull(message = "Contribution date missing")
    val date: LocalDate,
)