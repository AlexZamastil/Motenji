package com.motenji.DTO

import java.time.LocalDate

data class GoalContributionsDTO (
    val name: String,
    val measurable: Boolean,
    val deadline: LocalDate,
    val progress: Double,
    val contributions: List<com.motenji.DTO.ContributionDTO>
)