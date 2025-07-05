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
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {
    private val headers = arrayOf("Authorization", "content-type")
    private val exposedHeaders = arrayOf("Authorization")
    private val origins = System.getenv("fe_origin")?.split(", ")?.map { it.trim() }?.toTypedArray()?: arrayOf()
    private val methods = arrayOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
    private val jwtService = JwtService()

    @Bean
    fun filterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val authManager = reactiveAuthManager(jwtService = jwtService)
        val authFilter = AuthenticationWebFilter(authManager)
        authFilter.setServerAuthenticationConverter(JwtAuthConverter())
        authFilter.setRequiresAuthenticationMatcher(PathPatternParserServerWebExchangeMatcher("/user/**"))

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
    fun corsConfigurer(): WebFluxConfigurer {
        return object : WebFluxConfigurer {
            override fun addCorsMappings(registry: org.springframework.web.reactive.config.CorsRegistry) {
                registry.addMapping("/**")
                    .allowCredentials(false)
                    .allowedHeaders(*headers)
                    .exposedHeaders(*exposedHeaders)
                    .allowedOriginPatterns(*origins)
                    .allowedMethods(*methods)
            }
        }
    }
}