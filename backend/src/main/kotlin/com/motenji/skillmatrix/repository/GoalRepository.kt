package com.motenji.skillmatrix.repository

import com.motenji.skillmatrix.model.Goal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GoalRepository : JpaRepository<Goal, Long>{

    fun findGoalById(id: Long): Goal
    fun findGoalByName(name: String): Goal
}
