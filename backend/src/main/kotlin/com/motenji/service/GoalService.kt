package com.motenji.service

import com.motenji.DTO.ContributionDTO
import com.motenji.DTO.GoalCreateDTO
import com.motenji.DTO.GoalInfoDTO
import com.motenji.model.Contribution
import com.motenji.model.Goal
import com.motenji.repository.ContributionRepository
import com.motenji.repository.GoalRepository
import com.motenji.repository.UserRepository
import com.motenji.utility.ResponseFactory
import com.motenji.utility.ResponseWrapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.reactive.TransactionalOperator

@Service
class GoalService(
    val goalRepository: GoalRepository,
    val userRepository: UserRepository,
    val contributionRepository: ContributionRepository,
    private val transactionalOperator: TransactionalOperator
) {

    suspend fun createGoal(createDTO: GoalCreateDTO): ResponseEntity<ResponseWrapper<String>> {
        val goal = Goal(
            id = null,
            name = createDTO.name,
            measurable = createDTO.measurable,
            deadline = createDTO.deadline,
            progress = createDTO.progress,
            userId = userRepository.findUserById(createDTO.userId)?.id
                ?: return ResponseFactory.badRequest("User not found")
        )
        goalRepository.save(goal)
        return ResponseFactory.success("goal added successfully")
    }

    suspend fun getAllGoalsOfUser(userId: Long): ResponseEntity<ResponseWrapper<List<GoalInfoDTO>>> {
        val goals: List<GoalInfoDTO> = goalRepository.findAllByUserId(userId)
        return if (goals.isEmpty()) {
            ResponseFactory.notFound("Goals for this user not found")
        } else ResponseFactory.success(goals)
    }

    suspend fun contributeToGoal(
        goalId: Long,
        contributionDTO: ContributionDTO
    ): ResponseEntity<ResponseWrapper<String>> {
        val goal =
            goalRepository.findGoalById(goalId) ?: return ResponseFactory.notFound("Goal for this user not found")
        val contribution = Contribution(
            contributionId = null,
            name = contributionDTO.name,
            percentageIncrement = contributionDTO.percentageIncrement,
            goalId = goal.id!!,
            date = contributionDTO.date
        )
        calculateProgress(goal.id!!)
        contributionRepository.save(contribution)
        goalRepository.save(goal)
        return ResponseFactory.success("contribution added successfully")
    }

    suspend fun deleteGoal(goalId: Long): ResponseEntity<ResponseWrapper<String>> {
        val goal =
            goalRepository.findGoalById(goalId) ?: return ResponseFactory.notFound("Goal for this user not found")
        val goalContributions = contributionRepository.findAllByGoalId(goal.id)?: return ResponseFactory.notFound("Contribution for this user not found")
        transactionalOperator
        goalContributions.forEach { contributionRepository.delete(it) }
        goalRepository.delete(goal)
        return ResponseFactory.success("goal removed successfully")
    }

    suspend fun getGoalInfo(goalId: Long): ResponseEntity<ResponseWrapper<com.motenji.DTO.GoalContributionsDTO>> {
        val goal =
            goalRepository.findGoalById(goalId) ?: return ResponseFactory.notFound("Goal for this user not found")
        val contributions = contributionRepository.findAllByGoalId(goal.id)?: return ResponseFactory.notFound("Contribution for this user not found")
        val contributionsDTO: MutableList<ContributionDTO> = mutableListOf()
        contributions.forEach {
            contributionsDTO.add(
                ContributionDTO(
                    it.goalId,
                    it.name,
                    it.percentageIncrement,
                    it.date
                )
            )
        }
        val goalInfo = com.motenji.DTO.GoalContributionsDTO(
            name = goal.name,
            measurable = goal.measurable,
            deadline = goal.deadline,
            progress = goal.progress,
            contributions = contributionsDTO
        )
        return ResponseFactory.success(goalInfo)
    }

    suspend fun deleteContribution(contributionId: Long): ResponseEntity<ResponseWrapper<String>> {
        val contribution = contributionRepository.findByContributionId(contributionId)
            ?: return ResponseFactory.notFound("Contribution for this user not found")
        val goal = goalRepository.findGoalById(contribution.goalId)
            ?: return ResponseFactory.notFound("Goal for this user not found")
        calculateProgress(contribution.goalId)
        goalRepository.save(goal)
        contributionRepository.delete(contribution)
        return ResponseFactory.success("contribution deleted successfully")
    }

    suspend fun calculateProgress(goalId: Long): Boolean {
        val goal = goalRepository.findGoalById(goalId) ?: return false
        val contributions = contributionRepository.findAllByGoalId(goal.id)?: return false
        goal.progress = 0.0
        contributions.forEach {
            val increment = it.percentageIncrement?.toDouble() ?: 0.0
            goal.progress += increment
        }
        goalRepository.save(goal)
        return true
    }

}