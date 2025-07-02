package com.motenji.repository

import com.motenji.model.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CoroutineCrudRepository<User, Long>{
    suspend fun findUserById(id: Long): User?
    suspend  fun findUserByNickname(nickname: String): User?
    suspend fun existsUserByNickname(nickname: String): Boolean
}
