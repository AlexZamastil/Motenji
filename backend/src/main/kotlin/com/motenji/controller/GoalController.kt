package com.motenji.controller

import com.motenji.DTO.ContributionDTO
import com.motenji.DTO.GoalContributionsDTO
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
    fun createGoal(@RequestBody @Valid goalCreateDTO: GoalCreateDTO): ResponseEntity<com.motenji.utility.ResponseWrapper<String>> {
        return goalService.createGoal(goalCreateDTO)
    }
    @PostMapping("/contributeToGoal/{goalId}")
    fun contributeToGoal(@PathVariable("goalId") goalId: Long, @RequestBody @Valid goalContributionDTO: ContributionDTO): ResponseEntity<com.motenji.utility.ResponseWrapper<String>> {
        return goalService.contributeToGoal(goalId, goalContributionDTO)
    }

    @GetMapping("/getUserGoals/{userId}")
    fun getUserGoals(@PathVariable("userId") userId: Long): ResponseEntity<com.motenji.utility.ResponseWrapper<List<GoalInfoDTO>>> {
        return goalService.getAllGoalsOfUser(userId)
    }

    @GetMapping("/getGoalInfo/{goalId}")
    fun getGoalInfo(@PathVariable("goalId") goalId: Long): ResponseEntity<com.motenji.utility.ResponseWrapper<com.motenji.DTO.GoalContributionsDTO>> {
        return goalService.getGoalInfo(goalId)
    }

    @DeleteMapping("/deleteGoal/{goalId}")
    fun deleteGoal(@PathVariable("goalId") goalId: Long): ResponseEntity<com.motenji.utility.ResponseWrapper<String>> {
        return goalService.deleteGoal(goalId)
    }
    @DeleteMapping("/deleteContribution/{contributionId}")
    fun deleteContribution(@PathVariable("contributionId") contributionId: Long): ResponseEntity<com.motenji.utility.ResponseWrapper<String>> {
        return goalService.deleteContribution(contributionId)
    }
}