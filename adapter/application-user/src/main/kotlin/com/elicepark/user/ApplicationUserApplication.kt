package com.elicepark.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApplicationUserApplication

fun main(args: Array<String>) {
    runApplication<ApplicationUserApplication>(*args)
}
