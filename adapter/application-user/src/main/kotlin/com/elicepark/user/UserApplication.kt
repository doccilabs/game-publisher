package com.elicepark.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.elicepark"])
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
