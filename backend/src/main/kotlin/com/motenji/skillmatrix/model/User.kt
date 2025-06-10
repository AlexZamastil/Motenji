package com.motenji.skillmatrix.model

import com.motenji.skillmatrix.DTO.UserDTO
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val id: Long?,
    @Column(name = "name")
    val nickname: String,
    @Column(name = "password")
    val password: String,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val goals: List<Goal> = listOf()
)
fun convertToUserDTO(user: User): UserDTO {
    return UserDTO(user.nickname)
}

