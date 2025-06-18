package com.motenji.skillmatrix.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate

data class GoalContributionDTO(
    @field:NotBlank(message = "Goal missing")
    val goalId: Long,
    val contributionName: String,
    val contributionPercentage: Int?,
    @field:NotNull(message = "Contribution date missing")
    val date: LocalDate,
)