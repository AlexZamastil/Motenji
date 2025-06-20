package com.motenji.controller

import com.motenji.DTO.RegisterDTO
import com.motenji.DTO.UserDTO
import com.motenji.service.UserService
import com.motenji.utility.ResponseWrapper
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/")
class UserController(
    val userService: com.motenji.service.UserService
) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid registerDto: RegisterDTO): ResponseEntity<com.motenji.utility.ResponseWrapper<String>> {
        return userService.registerUser(registerDto)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid  userDTO: UserDTO): ResponseEntity<com.motenji.utility.ResponseWrapper<String>> {
        return userService.login(userDTO)
    }

    @GetMapping("/getDetails/{id}")
    fun getUser(@PathVariable id: String): ResponseEntity<com.motenji.utility.ResponseWrapper<UserDTO>> {
        return userService.getUserDetails(id.toLong())
    }
}