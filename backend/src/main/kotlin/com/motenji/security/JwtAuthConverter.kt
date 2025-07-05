package com.motenji.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class JwtAuthConverter: ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange?): Mono<Authentication> {
        val authHeader = exchange?.request?.headers?.getFirst("Authorization") ?: return Mono.empty()
        return if (authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            Mono.just(UsernamePasswordAuthenticationToken(token, token))
        } else {
            Mono.empty()
        }
    }
}