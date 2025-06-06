package com.motenji.skillmatrix.controller

import com.motenji.skillmatrix.service.GoalService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/goal/")
class GoalController (val goalService: GoalService){

}