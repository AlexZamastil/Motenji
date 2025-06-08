package com.motenji.skillmatrix.repository

import com.motenji.skillmatrix.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<User, Long>{

    fun findUserById(id: Long): User
}
