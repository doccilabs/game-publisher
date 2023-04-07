package com.elicepark.messaginginterface

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.elicepark"])
class MessagingInterfaceApplication

fun main(args: Array<String>) {
    runApplication<MessagingInterfaceApplication>(*args)
}
