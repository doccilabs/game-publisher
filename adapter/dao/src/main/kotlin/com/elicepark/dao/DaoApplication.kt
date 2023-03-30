package com.elicepark.dao

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.elicepark"])
class DaoApplication

fun main(args: Array<String>) {
    runApplication<DaoApplication>(*args)
}
