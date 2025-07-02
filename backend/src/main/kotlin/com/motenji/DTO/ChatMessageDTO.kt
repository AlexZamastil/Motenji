package com.motenji.DTO

import java.time.LocalDateTime

data class ChatMessageDTO(
    val user: String,
    val time: LocalDateTime = LocalDateTime.now(),
    val message: String
)