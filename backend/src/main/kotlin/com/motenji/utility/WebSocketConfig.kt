package com.motenji.utility

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketSession
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import reactor.core.publisher.Mono
import reactor.core.publisher.Sinks

@Configuration
class WebSocketConfig {

    private val messageSink = Sinks.many().multicast().directBestEffort<String>()

    @Bean
    fun webSocketHandler(): WebSocketHandler {
        return WebSocketHandler { session: WebSocketSession ->

            val incomingMessages = session.receive()
                .map {
                    it.payloadAsText
                }
                .doOnNext { message ->
                    println("received message: $message")
                    messageSink.tryEmitNext(message)
                }
                .then()

            val outgoingMessages = session.send(
                messageSink.asFlux()
                    .map { session.textMessage(it) }
            )
            Mono.zip(incomingMessages, outgoingMessages).then()

        }
    }

    @Bean
    fun handlerAdapter() = WebSocketHandlerAdapter()

    @Bean
    fun handleMapping(webSocketHandler: WebSocketHandler): HandlerMapping {
        val map = mapOf("/ws" to webSocketHandler)
        val mapping = SimpleUrlHandlerMapping()
        mapping.urlMap = map
        mapping.order = -1
        return mapping
    }
}

//MVC VERSION:
/*
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // temporary allow all origins, until it works smoothly
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*")
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic")
        registry.setApplicationDestinationPrefixes("/app")
    }
}*/