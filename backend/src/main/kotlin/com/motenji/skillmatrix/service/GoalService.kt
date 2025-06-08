package com.motenji.skillmatrix.service

import com.motenji.skillmatrix.DTO.GoalCreateDTO
import com.motenji.skillmatrix.repository.GoalRepository
import org.springframework.stereotype.Service

@Service
class GoalService(val goalRepository: GoalRepository) {
    fun createGoal(createDTO: GoalCreateDTO): String {
        goalRepository.findGoalById(1)
        return "ok";
    }


}