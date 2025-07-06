package com.motenji.security

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.Authentication
import reactor.core.publisher.Mono

class CustomReactiveAuthManager(private val jwtService: JwtService) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val token = authentication.credentials.toString()

        return if (jwtService.validateToken(token) && !jwtService.isTokenExpired(token)) {
            val userData = jwtService.getTokenData(token)
            if (userData != null) {
                val auth = UsernamePasswordAuthenticationToken(
                    userData.nickname,
                    token,
                    listOf(SimpleGrantedAuthority("ROLE_USER"))
                )
                Mono.just(auth)
            } else {
                Mono.empty()
            }
        } else {
            Mono.empty()
        }
    }
}
