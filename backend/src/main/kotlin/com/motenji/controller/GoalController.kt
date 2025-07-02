package com.motenji.controller

import com.motenji.DTO.ContributionDTO
import com.motenji.DTO.GoalCreateDTO
import com.motenji.DTO.GoalInfoDTO
import com.motenji.service.GoalService
import com.motenji.utility.ResponseWrapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/goal/")
class GoalController (val goalService: GoalService){

    @PostMapping("/createGoal")
    suspend fun createGoal(@RequestBody @Valid goalCreateDTO: GoalCreateDTO): ResponseEntity<ResponseWrapper<String>> {
        return goalService.createGoal(goalCreateDTO)
    }
    @PostMapping("/contributeToGoal/{goalId}")
    suspend fun contributeToGoal(@PathVariable("goalId") goalId: Long, @RequestBody @Valid goalContributionDTO: ContributionDTO): ResponseEntity<ResponseWrapper<String>> {
        return goalService.contributeToGoal(goalId, goalContributionDTO)
    }

    @GetMapping("/getUserGoals/{userId}")
    suspend fun getUserGoals(@PathVariable("userId") userId: Long): ResponseEntity<ResponseWrapper<List<GoalInfoDTO>>> {
        return goalService.getAllGoalsOfUser(userId)
    }

    @GetMapping("/getGoalInfo/{goalId}")
    suspend fun getGoalInfo(@PathVariable("goalId") goalId: Long): ResponseEntity<ResponseWrapper<com.motenji.DTO.GoalContributionsDTO>> {
        return goalService.getGoalInfo(goalId)
    }

    @DeleteMapping("/deleteGoal/{goalId}")
    suspend fun deleteGoal(@PathVariable("goalId") goalId: Long): ResponseEntity<ResponseWrapper<String>> {
        return goalService.deleteGoal(goalId)
    }
    @DeleteMapping("/deleteContribution/{contributionId}")
    suspend fun deleteContribution(@PathVariable("contributionId") contributionId: Long): ResponseEntity<ResponseWrapper<String>> {
        return goalService.deleteContribution(contributionId)
    }
}