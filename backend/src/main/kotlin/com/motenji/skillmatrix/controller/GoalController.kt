package com.motenji.skillmatrix.controller

import com.motenji.skillmatrix.DTO.GoalCreateDTO
import com.motenji.skillmatrix.service.GoalService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/goal/")
class GoalController (val goalService: GoalService){

    @PostMapping("/createGoal")
    fun createGoal(@RequestBody goalCreateDTO: GoalCreateDTO){
        goalService.createGoal(goalCreateDTO)
    }


}