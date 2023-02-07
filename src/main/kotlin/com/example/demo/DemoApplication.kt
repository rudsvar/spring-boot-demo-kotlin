package com.example.demo

import com.example.demo.config.SecurityConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@Import(SecurityConfig::class)
@EntityScan
@EnableJpaRepositories
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}