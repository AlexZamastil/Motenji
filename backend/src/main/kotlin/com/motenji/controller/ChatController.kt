package com.motenji.controller

import com.motenji.DTO.ChatMessageDTO
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    suspend fun sendChatMessage(chatMessageDTO: ChatMessageDTO): ChatMessageDTO {
        println("Sending a chat message!")
        return chatMessageDTO.copy()
    }
}