package com.motenji.skillmatrix.DTO

import java.time.LocalDate

data class GoalContributionDTO(
    val goalId: Long,
    val contributionName: String,
    val contributionPercentage: Int?,
    val date: LocalDate,
)