package com.elicepark.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.elicepark"])
class MessagingProducerApplication

fun main(args: Array<String>) {
    runApplication<MessagingProducerApplication>(*args)
}
