package com.motenji.skillmatrix.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "contribution")
data class Contribution(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contribution_id")
    val contributionId: Long?,
    @Column(name = "name")
    val name: String,
    @Column(name = "percentage")
    val percentageIncrement: Int?,
    @Column(name = "date")
    val date: LocalDate,
    @ManyToOne
    @JoinColumn(name = "goal_id")
    val goal: Goal
)