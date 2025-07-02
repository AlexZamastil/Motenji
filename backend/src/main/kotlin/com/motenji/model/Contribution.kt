package com.motenji.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table(name = "contribution")
data class Contribution(
    @Id
    @Column("contribution_id")
    val contributionId: Long?,
    @Column("name")
    val name: String,
    @Column("percentage")
    val percentageIncrement: Int?,
    @Column("date")
    val date: LocalDate,
    @Column("goal_id")
    val goalId: Long
)