package com.motenji.skillmatrix.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "goal")
data class Goal(
    @Id
    val id: Long,
    @Column(name = "name")
    val name: String,
    @Column(name = "description")
    val description: String,
    @Column(name = "createdTime", nullable = false)
    val createdTime: LocalDateTime = LocalDateTime.now(),
    @Column(name = "completed")
    var completed: Boolean = false,
    @Column(name = "measurable")
    var measurable: Boolean = false,
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: User
)