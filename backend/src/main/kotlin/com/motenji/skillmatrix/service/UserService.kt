package com.motenji.skillmatrix.service

import com.motenji.skillmatrix.DTO.UserDTO
import com.motenji.skillmatrix.repository.UserRepo
import org.springframework.stereotype.Service;

@Service
class UserService(val userRepo: UserRepo) {

        fun login(username:String, password:String){

        }

        fun registerUser(registerDto: UserDTO) {
            //userRepo.save(user)
        }
}
