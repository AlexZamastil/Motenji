package com.motenji.skillmatrix.controller

import com.motenji.skillmatrix.DTO.GoalCreateDTO
import com.motenji.skillmatrix.DTO.GoalInfoDTO
import com.motenji.skillmatrix.service.GoalService
import com.motenji.skillmatrix.utility.ResponseWrapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/goal/")
class GoalController (val goalService: GoalService){

    @PostMapping("/createGoal")
    fun createGoal(@RequestBody goalCreateDTO: GoalCreateDTO): ResponseEntity<ResponseWrapper<String>> {
        return goalService.createGoal(goalCreateDTO)
    }
    @GetMapping("/getUserGoals/{id}")
    fun getUserGoals(@PathVariable("id") userId: Long): ResponseEntity<ResponseWrapper<List<GoalInfoDTO>>> {
        return goalService.getAllGoalsOfUser(userId)
    }

}