package com.motenji.service

import com.motenji.DTO.RegisterDTO
import com.motenji.DTO.UserDTO
import com.motenji.model.User
import com.motenji.model.convertToUserDTO
import com.motenji.repository.UserRepository
import com.motenji.security.JwtService
import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.springframework.http.HttpStatus
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import io.mockk.mockk
import kotlin.test.assertFalse


class UserServiceTest {
    private lateinit var userRepo: UserRepository
    private lateinit var jwtService: JwtService
    private lateinit var userService: UserService

    private val username = "user123"
    private val correctPassword = "Password_1!"
    private val wrongPassword = "Different_Password"
    private val user = User(1, username, correctPassword)
    private val token = "mockedToken"

    @BeforeEach
    fun setup() {
        userRepo = mockk()
        jwtService = mockk()
        userService = UserService(userRepo, jwtService)
    }

    @Test
    fun testLoginHappyPath() = runTest {

        coEvery { userRepo.findUserByNickname(username) } returns user
        coEvery { jwtService.generateToken(user) } returns token
        val result = userService.login(UserDTO(1, username, correctPassword))

        assertEquals(result.statusCode, HttpStatus.OK)
        assertTrue(result.body?.data?.contains(token) ?: false, "Token must be in return data")
        assertTrue(result.body?.success ?: false, "result must be successful")

        coVerify(exactly = 1) { userRepo.findUserByNickname(any()) }
        coVerify(exactly = 1) { jwtService.generateToken(any()) }
    }

    @Test
    fun testLoginWrongPassword() = runTest {
        coEvery { userRepo.findUserByNickname(username) } returns user
        val result = userService.login(UserDTO(1, username, wrongPassword))

        assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
        assertFalse(result.body?.success ?: false, "result must be false")
        assertEquals(result.body?.message, "incorrect password")

        coVerify(exactly = 1) { userRepo.findUserByNickname(any()) }
        coVerify(exactly = 0) { jwtService.generateToken(any()) }
    }

    @Test
    fun testLoginNonexistentUser() = runTest {

        coEvery { userRepo.findUserByNickname(username) } returns null

        val result = userService.login(UserDTO(1, username, correctPassword))

        assertEquals(result.statusCode, HttpStatus.NOT_FOUND)
        assertFalse(result.body?.success ?: false, "result must be false")
        assertEquals(result.body?.message, "User not found")

        coVerify(exactly = 1) { userRepo.findUserByNickname(any()) }
        coVerify(exactly = 0) { jwtService.generateToken(any()) }
    }

    private val userID: Long = 1

    @Test
    fun getUserDetailsHappyPath() = runTest {
        coEvery { userRepo.findById((userID)) } returns user

        val result = userService.getUserDetails(userID)

        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body?.data, convertToUserDTO(user))
    }

    @Test
    fun getUserDetailsWrongId() = runTest {
        coEvery { userRepo.findById((userID)) } returns null

        val result = userService.getUserDetails(userID)

        assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
        assertEquals(result.body?.message, "User not found")
    }

    @Test
    fun registerUserHappyPath() = runTest {
        coEvery { userRepo.existsUserByNickname(username) } returns false
        coEvery { jwtService.generateToken(User(null, username, correctPassword)) } returns token
        coEvery { userRepo.save(User(null, username, correctPassword)) } returns user

        val result = userService.registerUser(RegisterDTO(username, correctPassword, correctPassword))

        assertEquals(result.statusCode, HttpStatus.OK)
        assertTrue(result.body?.data?.contains(token) ?: false, "Token must be in return data")
        assertTrue(result.body?.success ?: false, "result must be successful")
    }

    @Test
    fun registerUserNotMatchingPassword() = runTest {
        coEvery { userRepo.existsUserByNickname(username) } returns false

        val result = userService.registerUser(RegisterDTO(username, correctPassword, wrongPassword))

        assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
        assertEquals(result.body?.message, "Passwords don't match")

        coVerify(exactly = 0) { userRepo.findUserByNickname(any()) }
        coVerify(exactly = 0) { jwtService.generateToken(any()) }
    }

    @Test
    fun registerExistingUser() = runTest {
        coEvery { userRepo.existsUserByNickname(username) } returns true

        val result = userService.registerUser(RegisterDTO(username, correctPassword, correctPassword))

        assertEquals(result.statusCode, HttpStatus.BAD_REQUEST)
        assertEquals(result.body?.message, "User with the same nickname already exists")

        coVerify(exactly = 0) { userRepo.findUserByNickname(any()) }
        coVerify(exactly = 0) { jwtService.generateToken(any()) }
    }


}