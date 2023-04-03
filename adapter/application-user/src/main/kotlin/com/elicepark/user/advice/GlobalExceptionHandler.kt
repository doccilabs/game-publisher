package com.elicepark.user.advice

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author Brian
 * @since 2023/03/31
 */
@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

}