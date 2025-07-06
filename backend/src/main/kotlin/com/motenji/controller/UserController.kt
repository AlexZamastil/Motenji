package com.motenji.controller

import com.motenji.DTO.RegisterDTO
import com.motenji.DTO.UserDTO
import com.motenji.utility.ResponseFactory
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
    suspend fun register(@RequestBody @Valid registerDto: RegisterDTO): ResponseEntity<ResponseWrapper<String>> {
        return userService.registerUser(registerDto)
    }

    @PostMapping("/login")
    suspend fun login(@RequestBody @Valid  userDTO: UserDTO): ResponseEntity<ResponseWrapper<String>> {
        return userService.login(userDTO)
    }

    @GetMapping("/getDetails/{id}")
    suspend fun getUser(@PathVariable id: String): ResponseEntity<ResponseWrapper<UserDTO>> {
        return userService.getUserDetails(id.toLong())
    }
}