package com.motenji.repository

import com.motenji.DTO.GoalInfoDTO
import com.motenji.model.Goal
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalRepository : CoroutineCrudRepository<Goal, Long>{

    suspend fun findGoalById(id: Long): Goal?
    suspend fun findGoalByName(name: String): Goal
    suspend fun findAllByUserId(userId: Long): List<GoalInfoDTO>
}
