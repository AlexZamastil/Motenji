package com.motenji.skillmatrix.service

import com.motenji.skillmatrix.DTO.GoalContributionDTO
import com.motenji.skillmatrix.DTO.GoalCreateDTO
import com.motenji.skillmatrix.DTO.GoalInfoDTO
import com.motenji.skillmatrix.model.Contribution
import com.motenji.skillmatrix.model.Goal
import com.motenji.skillmatrix.repository.ContributionRepository
import com.motenji.skillmatrix.repository.GoalRepository
import com.motenji.skillmatrix.repository.UserRepository
import com.motenji.skillmatrix.utility.ResponseFactory
import com.motenji.skillmatrix.utility.ResponseWrapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GoalService(
    val goalRepository: GoalRepository,
    val userRepository: UserRepository,
    val contributionRepository: ContributionRepository
) {

    fun createGoal(createDTO: GoalCreateDTO): ResponseEntity<ResponseWrapper<String>> {
        val goal = Goal(
            id = null,
            name = createDTO.name,
            measurable = createDTO.measurable,
            deadline = createDTO.deadline,
            progress = createDTO.progress,
            user = userRepository.findUserById(createDTO.userId) ?: return ResponseFactory.badRequest("User not found")
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

    fun contributeToGoal(goalId: Long, contributionDTO: GoalContributionDTO): ResponseEntity<ResponseWrapper<String>> {
        val goal = goalRepository.findGoalById(goalId) ?: return ResponseFactory.notFound("Goal for this user not found")
        val contribution = Contribution(
                contributionId = null,
                name = contributionDTO.contributionName,
                contributionPercentage = contributionDTO.contributionPercentage,
                goal = goal,
                date = contributionDTO.date
                )
        goal.updateProgress(contribution)
        contributionRepository.save(contribution)
        goalRepository.save(goal)
        return ResponseFactory.success("contribution added successfully")
    }

}