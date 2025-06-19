package com.motenji.skillmatrix.service

import com.motenji.skillmatrix.DTO.ContributionDTO
import com.motenji.skillmatrix.DTO.GoalContributionsDTO
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
        val goals: List<GoalInfoDTO> = goalRepository.findAllByUserId(userId)
        return if (goals.isEmpty()) {
            ResponseFactory.notFound("Goals for this user not found")
        } else ResponseFactory.success(goals)
    }

    fun contributeToGoal(goalId: Long, contributionDTO: ContributionDTO): ResponseEntity<ResponseWrapper<String>> {
        val goal =
            goalRepository.findGoalById(goalId) ?: return ResponseFactory.notFound("Goal for this user not found")
        val contribution = Contribution(
            contributionId = null,
            name = contributionDTO.name,
            percentageIncrement = contributionDTO.percentageIncrement,
            goal = goal,
            date = contributionDTO.date
        )
        calculateProgress(goal.id!!)
        contributionRepository.save(contribution)
        goalRepository.save(goal)
        return ResponseFactory.success("contribution added successfully")
    }

    fun deleteGoal(goalId: Long): ResponseEntity<ResponseWrapper<String>> {
        val goal =
            goalRepository.findGoalById(goalId) ?: return ResponseFactory.notFound("Goal for this user not found")
        val goalContributions = contributionRepository.findAllByGoal(goal)
        goalContributions.forEach { contributionRepository.delete(it) }
        goalRepository.delete(goal)
        return ResponseFactory.success("goal removed successfully")
    }

    fun getGoalInfo(goalId: Long): ResponseEntity<ResponseWrapper<GoalContributionsDTO>> {
        val goal =
            goalRepository.findGoalById(goalId) ?: return ResponseFactory.notFound("Goal for this user not found")
        val contributions = contributionRepository.findAllByGoal(goal)
        val contributionsDTO: MutableList<ContributionDTO> = mutableListOf()
        contributions.forEach {
            contributionsDTO.add(
                ContributionDTO(
                    it.goal.id ?: return ResponseFactory.badRequest("Goal ID problem"),
                    it.name,
                    it.percentageIncrement,
                    it.date
                )
            )
        }
        val goalInfo = GoalContributionsDTO(
            name = goal.name,
            measurable = goal.measurable,
            deadline = goal.deadline,
            progress = goal.progress,
            contributions = contributionsDTO
        )
        return ResponseFactory.success(goalInfo)
    }

    fun deleteContribution(contributionId: Long): ResponseEntity<ResponseWrapper<String>> {
        val contribution = contributionRepository.findByContributionId(contributionId)
            ?: return ResponseFactory.notFound("Contribution for this user not found")
        val goal = contribution.goal
        calculateProgress(goal.id!!)
        goalRepository.save(goal)
        contributionRepository.delete(contribution)
        return ResponseFactory.success("contribution deleted successfully")
    }

    fun calculateProgress(goalId: Long): Boolean {
        val goal = goalRepository.findGoalById(goalId)?: return false
        val contributions = contributionRepository.findAllByGoal(goal)
        goal.progress = 0.0
        contributions.forEach {
            val increment = it.percentageIncrement?.toDouble() ?: 0.0
            goal.progress+=increment
        }
        goalRepository.save(goal)
        return true
    }

}