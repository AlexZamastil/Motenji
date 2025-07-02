package com.motenji.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Table("goal")
data class Goal(
    @Id
    @Column("goal_id")
    var id: Long? = null,
    @Column("name")
    val name: String,
    @Column("measurable")
    val measurable: Boolean,
    @Column("deadline")
    val deadline: LocalDate,
    @Column("progress")
    var progress: Double,
    @Column("created")
    val created: LocalDateTime = LocalDateTime.now(),
    @Column("user_id")
    val userId: Long
)



