package com.motenji.skillmatrix.service

import com.motenji.skillmatrix.DTO.RegisterDTO
import com.motenji.skillmatrix.DTO.UserDTO
import com.motenji.skillmatrix.model.convertToUserDTO
import com.motenji.skillmatrix.repository.UserRepository
import com.motenji.skillmatrix.utility.ResponseFactory
import com.motenji.skillmatrix.utility.ResponseWrapper
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service;

@Service
class UserService(val userRepo: UserRepository) {

        fun login(username:String, password:String){

        }

        fun registerUser(registerDto: RegisterDTO) {
            if (registerDto.password != registerDto.passwordConfirm){
                    return
            }
        }

        fun getUserDetails(id:Long): ResponseEntity<ResponseWrapper<UserDTO>> {
            val userDetails = userRepo.findById(id).orElse(null)
            return userDetails?.let { ResponseFactory.success(convertToUserDTO(it)) } ?: ResponseFactory.notFound("User with ID $id not found")
        }

}
