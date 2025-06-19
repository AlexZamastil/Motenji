package com.motenji.skillmatrix.controller

import com.motenji.skillmatrix.DTO.ContributionDTO
import com.motenji.skillmatrix.DTO.GoalContributionsDTO
import com.motenji.skillmatrix.DTO.GoalCreateDTO
import com.motenji.skillmatrix.DTO.GoalInfoDTO
import com.motenji.skillmatrix.service.GoalService
import com.motenji.skillmatrix.utility.ResponseWrapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/goal/")
class GoalController (val goalService: GoalService){

    @PostMapping("/createGoal")
    fun createGoal(@RequestBody @Valid goalCreateDTO: GoalCreateDTO): ResponseEntity<ResponseWrapper<String>> {
        return goalService.createGoal(goalCreateDTO)
    }
    @PostMapping("/contributeToGoal/{goalId}")
    fun contributeToGoal(@PathVariable("goalId") goalId: Long, @RequestBody @Valid goalContributionDTO: ContributionDTO): ResponseEntity<ResponseWrapper<String>> {
        return goalService.contributeToGoal(goalId, goalContributionDTO)
    }

    @GetMapping("/getUserGoals/{userId}")
    fun getUserGoals(@PathVariable("userId") userId: Long): ResponseEntity<ResponseWrapper<List<GoalInfoDTO>>> {
        return goalService.getAllGoalsOfUser(userId)
    }

    @GetMapping("/getGoalInfo/{goalId}")
    fun getGoalInfo(@PathVariable("goalId") goalId: Long): ResponseEntity<ResponseWrapper<GoalContributionsDTO>> {
        return goalService.getGoalInfo(goalId)
    }

    @DeleteMapping("/deleteGoal/{goalId}")
    fun deleteGoal(@PathVariable("goalId") goalId: Long): ResponseEntity<ResponseWrapper<String>> {
        return goalService.deleteGoal(goalId)
    }
    @DeleteMapping("/deleteContribution/{contributionId}")
    fun deleteContribution(@PathVariable("contributionId") contributionId: Long): ResponseEntity<ResponseWrapper<String>> {
        return goalService.deleteContribution(contributionId)
    }
}