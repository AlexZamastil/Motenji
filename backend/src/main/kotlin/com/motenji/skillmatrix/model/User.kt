package com.motenji.skillmatrix.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class User(
    @Id
    val id: Long,
    @Column(name = "id")
    val nickname: String,
    @Column(name = "password")
    val password: String,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val goals: List<Goal> = listOf()
)
