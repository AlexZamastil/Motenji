package com.motenji.skillmatrix.controller

import com.motenji.skillmatrix.DTO.UserDTO
import com.motenji.skillmatrix.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/")
class UserController(val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody registerDto: UserDTO){
        userService.registerUser(registerDto)
    }
    @PostMapping("/login")
    fun login(@RequestBody nickname: String, password: String){
        userService.login(nickname,password)
    }

    @GetMapping("/get/{id}")
    fun getUser(@PathVariable id: String){
        //todo
    }
}