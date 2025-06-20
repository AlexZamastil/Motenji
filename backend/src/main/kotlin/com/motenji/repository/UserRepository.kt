package com.motenji.repository

import com.motenji.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>{
    fun findUserById(id: Long): User?
    fun findUserByNickname(nickname: String): User?
    fun existsUserByNickname(nickname: String): Boolean
}
