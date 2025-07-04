package com.motenji.DTO

import java.time.LocalDate

data class ContributionDTO(
    val goalId: Long,
    val name: String,
    val percentageIncrement: Int?,
    val date: LocalDate,
)