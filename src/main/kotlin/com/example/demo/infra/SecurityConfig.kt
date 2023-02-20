package com.example.demo.infra

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import java.time.OffsetDateTime
import java.util.*

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
class SecurityConfig {

    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain {
        return http.csrf().disable().authorizeHttpRequests { req -> req.anyRequest().permitAll() }.build()
    }

    @Bean("auditingDateTimeProvider")
    fun dateTimeProvider(): DateTimeProvider {
        return DateTimeProvider { Optional.of(OffsetDateTime.now()) }
    }
}