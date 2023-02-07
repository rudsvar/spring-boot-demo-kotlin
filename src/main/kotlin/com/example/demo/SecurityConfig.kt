package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

class SecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http.cors().disable().authorizeHttpRequests { req -> req.anyRequest().permitAll() }.build()
    }
}