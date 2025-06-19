package com.motenji.skillmatrix.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "goal")
data class Goal(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goal_id")
    var id: Long? = null,
    @Column(name = "name")
    val name: String,
    @Column(name = "measurable")
    val measurable: Boolean,
    @Column(name = "deadline")
    val deadline: LocalDate,
    @Column(name = "progress")
    var progress: Double,
    @Column(name = "created")
    val created: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)



