package com.motenji.skillmatrix.model

import jakarta.persistence.*

@Entity
@Table(name = "goal")
data class Goal(
    @Id
    @Column(name = "goal_id")
    val id: Long,
    @Column(name = "name")
    val name: String,
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)


