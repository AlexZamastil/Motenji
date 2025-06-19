package com.motenji.skillmatrix.service

import com.motenji.skillmatrix.DTO.RegisterDTO
import com.motenji.skillmatrix.DTO.UserDTO
import com.motenji.skillmatrix.model.User
import com.motenji.skillmatrix.model.convertToUserDTO
import com.motenji.skillmatrix.repository.UserRepository
import com.motenji.skillmatrix.security.JwtService
import com.motenji.skillmatrix.utility.ResponseFactory
import com.motenji.skillmatrix.utility.ResponseWrapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepo: UserRepository,
    private val jwtService: JwtService
) {

    fun login(userDTO: UserDTO): ResponseEntity<ResponseWrapper<String>> {
        val userId = userDTO.id ?: return ResponseFactory.notFound("User info missing")
        val user = userRepo.findUserById(userId) ?: return ResponseFactory.notFound("User not found")
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
        val user = User(null, registerDto.username, registerDto.password, emptyList())
        userRepo.save(user)
        val token = jwtService.generateToken(user)
        return ResponseFactory.success("User registered with token: $token")
    }

    fun getUserDetails(id: Long): ResponseEntity<ResponseWrapper<UserDTO>> {
        val userDetails = userRepo.findById(id).orElse(null)
        return userDetails?.let { ResponseFactory.success(convertToUserDTO(it)) }
            ?: ResponseFactory.notFound("User with ID $id not found")
    }

}
