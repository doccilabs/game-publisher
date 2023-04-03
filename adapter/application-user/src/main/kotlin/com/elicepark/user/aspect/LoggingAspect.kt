package com.elicepark.user.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author Brian
 * @since 2023/03/31
 */
@Aspect
@Component
class LoggingAspect {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @Pointcut("within(com.elicepark.user.controller..*)")
    fun allControllerPointcut() {}

    @AfterReturning(pointcut = "allControllerPointcut()", returning = "returnValue")
    fun writeSuccessLog(joinPoint: JoinPoint, returnValue: Any) {
        val methodName = joinPoint.signature.name
        val className = joinPoint.signature.declaringTypeName
        val args: Array<Any> = joinPoint.args

        val logMessage = "${className}.${methodName}(${Arrays.toString(args)}) returned ${returnValue}"

        logger.info(logMessage)
    }

    @AfterThrowing(pointcut = "allControllerPointcut()", throwing = "exception")
    fun writeFailLog(joinPoint: JoinPoint, exception: RuntimeException) {
        val methodName = joinPoint.signature.name
        val className = joinPoint.signature.declaringTypeName
        val args: Array<Any> = joinPoint.args

        val logMessage = "${className}.${methodName}(${Arrays.toString(args)}): ${exception.message}"
    }
}