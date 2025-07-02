package com.motenji.model

import com.motenji.DTO.UserDTO
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
    @Id
    @Column("user_id")
    val id: Long?,
    @Column("name")
    val nickname: String,
    @Column("password")
    val password: String,
)
fun convertToUserDTO(user: User): UserDTO {
    return UserDTO(user.id, user.nickname, user.password)
}

