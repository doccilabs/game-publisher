package com.elicepark.domain

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication(scanBasePackages = ["com.elicepark"])
@EnableJpaAuditing
class DomainApplication

fun main(args: Array<String>) {
	runApplication<DomainApplication>(*args)
}
