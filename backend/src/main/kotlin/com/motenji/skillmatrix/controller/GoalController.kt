package com.motenji.skillmatrix.controller

import com.motenji.skillmatrix.DTO.GoalContributionDTO
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
    fun contributeToGoal(@PathVariable("goalId") goalId: Long, @RequestBody @Valid goalContributionDTO: GoalContributionDTO): ResponseEntity<ResponseWrapper<String>> {
        return goalService.contributeToGoal(goalId, goalContributionDTO)
    }

    @GetMapping("/getUserGoals/{userId}")
    fun getUserGoals(@PathVariable("userId") userId: Long): ResponseEntity<ResponseWrapper<List<GoalInfoDTO>>> {
        return goalService.getAllGoalsOfUser(userId)
    }

}