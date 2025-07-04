package com.motenji.service

import com.motenji.DTO.GoalCreateDTO
import jakarta.validation.Validation
import jakarta.validation.Validator
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GoalCreateDTOTest {

    private lateinit var validator: Validator

    private val validName = "Run 10km"
    private val validDate = LocalDate.of(2025, 12, 31)
    private val validUserId: Long = 1

    @BeforeEach
    fun setup() {
        validator = Validation.buildDefaultValidatorFactory().validator
    }

    @Test
    fun goalCreateDTOHappyPath() = runTest {
        val dto = GoalCreateDTO(
            name = validName,
            measurable = true,
            deadline = validDate,
            progress = 0.0,
            userId = validUserId
        )

        val violations = validator.validate(dto)
        assertTrue(violations.isEmpty(), "DTO should be valid with correct input")
    }

    @Test
    fun goalCreateDTOBlankName() = runTest {
        val dto = GoalCreateDTO(
            name = "",
            measurable = true,
            deadline = validDate,
            progress = 0.0,
            userId = validUserId
        )

        val violations = validator.validate(dto)
        assertEquals(2, violations.size, "Should trigger @NotBlank and @Size violations on name")
        assertTrue(violations.all { it.propertyPath.toString() == "name" })
    }

    @Test
    fun goalCreateDTOShortName() = runTest {
        val shortName = "ab"
        val dto = GoalCreateDTO(
            name = shortName,
            measurable = false,
            deadline = validDate,
            progress = 0.0,
            userId = validUserId
        )

        val violations = validator.validate(dto)
        assertEquals(1, violations.size, "Expected violation is short goal name")
        assertTrue(violations.any { it.propertyPath.toString() == "name" })
    }

    @Test
    fun goalCreateDTOLongName() = runTest {
        val longName = "a".repeat(101)
        val dto = GoalCreateDTO(
            name = longName,
            measurable = true,
            deadline = validDate,
            progress = 0.0,
            userId = validUserId
        )

        val violations = validator.validate(dto)
        assertEquals(1, violations.size, "Expected violation is too goal name")
        assertTrue(violations.any { it.propertyPath.toString() == "name" })
    }
}
