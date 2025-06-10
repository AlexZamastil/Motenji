package com.motenji.skillmatrix.DTO

import java.time.LocalDate

data class GoalInfoDTO(
    val name: String,
    val measurable: Boolean,
    val deadline: LocalDate,
    val progress: Double
)