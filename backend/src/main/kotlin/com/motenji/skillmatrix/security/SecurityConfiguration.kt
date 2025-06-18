package com.motenji.skillmatrix.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
class SecurityConfiguration {
    private val HEADERS = arrayOf("Authorization", "content-type")
    private val ORIGINS = arrayOf(System.getenv("fe_origin"))
    private val METHODS = arrayOf("GET", "POST", "PUT", "DELETE")
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http.cors{}
            .csrf {it.disable()}
            .authorizeHttpRequests {
                it.requestMatchers("/goal/**").authenticated()
                it.requestMatchers("/user/**").authenticated()
                it.anyRequest().permitAll()
            }
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedHeaders(*HEADERS)
                    .allowedOrigins(*ORIGINS)
                    .allowedMethods(*METHODS)
            }
        }
    }

}