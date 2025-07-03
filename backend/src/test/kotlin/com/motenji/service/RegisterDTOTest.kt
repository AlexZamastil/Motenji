package com.motenji.service

import com.motenji.DTO.RegisterDTO
import jakarta.validation.Validation
import jakarta.validation.Validator
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RegisterDTOTest {
    private lateinit var validator: Validator

    private val username = "user123"
    private val password = "Password_1!"


    @BeforeEach
    fun setup() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    @Test
    fun registerUserDTOHappyPath() = runTest {

        val testDto = RegisterDTO(username, password, password)

        val violations = validator.validate(testDto)

        assertTrue(violations.isEmpty(), "Validation for correct input failed")
    }

    @Test
    fun registerUserDTOShortUsername() = runTest {
        val shortUsername = "a"
        val testDto = RegisterDTO(shortUsername, password, password)

        val violations = validator.validate(testDto)

        assertEquals(violations.size, 1)
        assertTrue(violations.any {
            it.propertyPath.toString() == "username"
        }, "Violation should be on username")
    }

    @Test
    fun registerUserDTOLongUsername() = runTest {
        val longUsername = "abcdefghijkmnopqrstuvw"
        val testDto = RegisterDTO(longUsername, password, password)

        val violations = validator.validate(testDto)

        assertEquals(violations.size, 1)
        assertTrue(violations.any {
            it.propertyPath.toString() == "username"
        }, "Violation should be on username")
    }
    @Test
    fun registerUserDTOBlankUsername() = runTest {
        val blankUsername = ""
        val testDto = RegisterDTO(blankUsername, password, password)

        val violations = validator.validate(testDto)

        assertEquals(violations.size, 2) // validation error for @NotBlank and @Length
        assertTrue(violations.any {
            it.propertyPath.toString() == "username"
        }, "Violation should be on username")
    }

    @Test
    fun registerUserDTOShortPassword() = runTest {
        val shortPassword = "12345"
        val testDto = RegisterDTO(username, shortPassword, shortPassword)

        val violations = validator.validate(testDto)

        assertEquals(violations.size, 1)
        assertTrue(violations.any {
            it.propertyPath.toString() == "password"
        }, "Violation should be on password")
    }

    @Test
    fun registerUserDTOLongPassword() = runTest {
        val longPassword = "123456789101112131415"
        val testDto = RegisterDTO(username, longPassword, longPassword)

        val violations = validator.validate(testDto)

        assertEquals(violations.size, 1)
        assertTrue(violations.any {
            it.propertyPath.toString() == "password"
        }, "Violation should be on password")

    }
    @Test
    fun registerUserDTOBlankPassword() = runTest {
        val blankPassword = ""
        val testDto = RegisterDTO(username, blankPassword, blankPassword)

        val violations = validator.validate(testDto)

        assertEquals(violations.size, 3) // validation error for @NotBlank, @Length and @NotBlank on password confirm
        assertTrue(violations.any {
            it.propertyPath.toString() == "password" || it.propertyPath.toString() == "passwordConfirm"
        }, "Violation should be on password")
    }
}