package com.motenji.repository

import com.motenji.DTO.GoalInfoDTO
import com.motenji.model.Goal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalRepository : JpaRepository<Goal, Long>{

    fun findGoalById(id: Long): Goal?
    fun findGoalByName(name: String): Goal
    fun findAllByUserId(userId: Long): List<GoalInfoDTO>
}
