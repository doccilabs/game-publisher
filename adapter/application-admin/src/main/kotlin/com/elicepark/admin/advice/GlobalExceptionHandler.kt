package com.elicepark.admin.advice

import com.elicepark.common.exceptions.GameContinuouslyAssignedException
import com.elicepark.common.exceptions.GameTimeConflictException
import com.elicepark.common.exceptions.InsideFiveDaysException
import com.elicepark.common.result.ErrorResults
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
@RestControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(WebExchangeBindException::class)
    fun handleConstraintViolationException(exception: WebExchangeBindException): ResponseEntity<ErrorResults.Response> {
        val responseBody = ErrorResults.Response.of(exception)

        return ResponseEntity.status(400)
            .body(responseBody)
    }

    @ExceptionHandler(InsideFiveDaysException::class)
    fun handleInsideFiveDaysException(exception: InsideFiveDaysException): ResponseEntity<ErrorResults.Response> {
        val responseBody = ErrorResults.Response.of(exception.fieldError)

        return ResponseEntity.status(400)
            .body(responseBody)
    }

    @ExceptionHandler(GameContinuouslyAssignedException::class)
    fun handleGameContinuouslyAssignedException(exception: GameContinuouslyAssignedException): ResponseEntity<ErrorResults.Response> {
        val responseBody = ErrorResults.Response.of(exception.errorCode)

        return ResponseEntity.status(exception.errorCode.status)
            .body(responseBody)
    }

    @ExceptionHandler(GameTimeConflictException::class)
    fun handleGameTimeConflictException(exception: GameTimeConflictException): ResponseEntity<ErrorResults.Response> {
        val responseBody = ErrorResults.Response.of(exception.errorCode)

        return ResponseEntity.status(exception.errorCode.status)
            .body(responseBody)
    }
}