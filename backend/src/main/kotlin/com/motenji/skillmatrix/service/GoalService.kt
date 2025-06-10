package com.motenji.skillmatrix.service

import com.motenji.skillmatrix.DTO.GoalCreateDTO
import com.motenji.skillmatrix.DTO.GoalInfoDTO
import com.motenji.skillmatrix.model.Goal
import com.motenji.skillmatrix.repository.GoalRepository
import com.motenji.skillmatrix.repository.UserRepository
import com.motenji.skillmatrix.utility.ResponseFactory
import com.motenji.skillmatrix.utility.ResponseWrapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GoalService(
    val goalRepository: GoalRepository,
    private val userRepository: UserRepository,
    repository: UserRepository
) {

    fun createGoal(createDTO: GoalCreateDTO): ResponseEntity<ResponseWrapper<String>> {
        val goal = Goal(
            id = null,
            name = createDTO.name,
            measurable = createDTO.measurable,
            deadline = createDTO.deadline,
            progress = createDTO.progress,
            user = userRepository.findUserById(createDTO.userId)
            )
        goalRepository.save(goal)
        return ResponseFactory.success("goal added successfully")
    }

    fun getAllGoalsOfUser(userId: Long): ResponseEntity<ResponseWrapper<List<GoalInfoDTO>>> {
         val goals : List<GoalInfoDTO> = goalRepository.findAllByUserId(userId)
        return if (goals.isEmpty()) {
            ResponseFactory.notFound("Goals for this user not found")
        } else ResponseFactory.success(goals)
    }

}