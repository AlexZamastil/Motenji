package com.motenji.service

import com.motenji.DTO.RegisterDTO
import com.motenji.DTO.UserDTO
import com.motenji.model.User
import com.motenji.model.convertToUserDTO
import com.motenji.repository.UserRepository
import com.motenji.security.JwtService
import com.motenji.utility.ResponseFactory
import com.motenji.utility.ResponseWrapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepo: UserRepository,
    private val jwtService: JwtService
) {

    fun login(userDTO: UserDTO): ResponseEntity<ResponseWrapper<String>> {
        val user = userRepo.findUserByNickname(userDTO.username) ?: return ResponseFactory.notFound("User not found")
        if (user.password == userDTO.password) {
            val token = jwtService.generateToken(user)
            return ResponseFactory.success("login successful with token: $token")
        } else {
            return ResponseFactory.badRequest("incorrect password")
        }
    }

    fun registerUser(registerDto: RegisterDTO): ResponseEntity<ResponseWrapper<String>> {
        if (registerDto.password != registerDto.passwordConfirm) {
            return ResponseFactory.badRequest("Passwords don't match")
        }
        if (userRepo.existsUserByNickname(registerDto.username)) {
            return ResponseFactory.badRequest("User with the same nickname already exists")
        }
        val user = User(null, registerDto.username, registerDto.password, emptyList())
        userRepo.save(user)
        val token = jwtService.generateToken(user)
        return ResponseFactory.success("User registered with token: $token")
    }

    fun getUserDetails(id: Long): ResponseEntity<com.motenji.utility.ResponseWrapper<UserDTO>> {
        val userDetails = userRepo.findById(id).orElse(null)
        return userDetails?.let { ResponseFactory.success(convertToUserDTO(it)) }
            ?: ResponseFactory.notFound("User with ID $id not found")
    }

}
