package com.elicepark.user.controller

import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Brian
 * @since 2023/03/31
 */
@RestController
@RequestMapping("/health")
class HealthCheckController {
    @GetMapping("")
    suspend fun healthCheck(): String = coroutineScope {
        return@coroutineScope "OK!"
    }
}