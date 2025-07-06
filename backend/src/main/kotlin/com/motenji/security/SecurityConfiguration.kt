package com.motenji.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {
    private val headers = arrayOf("Authorization", "content-type")
    private val exposedHeaders = arrayOf("Authorization")
    private val origins = System.getenv("FE_ORIGIN")?.split(", ")?.map { it.trim() }?.toTypedArray()?: arrayOf()
    private val methods = arrayOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
    private val jwtService = JwtService()

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val authManager = reactiveAuthManager(jwtService = jwtService)
        val authFilter = AuthenticationWebFilter(authManager)
        authFilter.setServerAuthenticationConverter(JwtAuthConverter(jwtService))
        authFilter.setRequiresAuthenticationMatcher(PathPatternParserServerWebExchangeMatcher("/**"))

       return http.cors{}
            .csrf {it.disable()}
            .authorizeExchange {
                it.pathMatchers("/ws").permitAll()
                it.pathMatchers("/user/register").permitAll()
                it.pathMatchers("/user/login").permitAll()
                it.pathMatchers("/goal/**").authenticated()
                it.pathMatchers("/user/**").authenticated()
                it.anyExchange().authenticated()
            }
           .addFilterAt(authFilter, SecurityWebFiltersOrder.AUTHENTICATION)
           .build()
    }
    @Bean
    fun reactiveAuthManager(jwtService: JwtService): ReactiveAuthenticationManager = CustomReactiveAuthManager(jwtService = jwtService)

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowCredentials = false
        configuration.allowedHeaders = headers.asList()
        configuration.allowedMethods = methods.asList()
        configuration.exposedHeaders = exposedHeaders.asList()
        configuration.allowedOriginPatterns = origins.asList()

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}