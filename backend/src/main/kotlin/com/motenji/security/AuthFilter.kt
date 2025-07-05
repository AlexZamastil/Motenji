package com.motenji.security
/*
import com.motenji.DTO.JWTDataDTO
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthFilter(private val jwtService: JwtService) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        println("request ${request.method} ${request.requestURI} ${request.headerNames}")
        val authorizationHeader = request.getHeader("Authorization")
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            val token = authorizationHeader.substring(7)
            if (!jwtService.isTokenExpired(token) && jwtService.validateToken(token)) {
                val userData: JWTDataDTO? = jwtService.getTokenData(token)
                if (userData != null) {
                    val authentication = UsernamePasswordAuthenticationToken(
                            userData,null, listOf(SimpleGrantedAuthority("ROLE_USER"))
                    )
                    SecurityContextHolder.getContext().authentication = authentication
                    println("Authentication set for user: ${userData.nickname}")
                } else {
                    println("UserData from token is null")
                }
            } else {
                println("Token is expired or invalid")
            }
        } else {
            println("No Authorization header or it does not start with Bearer")
        }

        filterChain.doFilter(request, response)
    }

}*/