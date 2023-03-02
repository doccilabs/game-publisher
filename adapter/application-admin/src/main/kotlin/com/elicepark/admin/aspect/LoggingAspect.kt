package com.elicepark.admin.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Arrays

/**
 * @author Brian
 * @since 2023/03/01
 */
@Aspect
@Component
class LoggingAspect {

    private val logger = LoggerFactory.getLogger(this::class.java)

    // com.elicepark.admin.controller 하위의 모든 컨트롤러 대상으로 포인트컷 적용
    @Pointcut("within(com.elicepark.admin.controller..*)")
    fun allControllerPointcut() {}

    // 성공한 요청에 대해서 로그를 찍는 메소드
    @AfterReturning(pointcut = "allControllerPointcut()", returning = "returnValue")
    fun writeSuccessLog(joinPoint: JoinPoint, returnValue: Any): Unit {
        val methodName = joinPoint.signature.name
        val className = joinPoint.signature.declaringTypeName
        val args: Array<Any> = joinPoint.args

        val logMessage = "${className}.${methodName}(${Arrays.toString(args)}) returned ${returnValue}"

        logger.info(logMessage)
    }

    // 처리 실패한 요청에 대해서 로그를 찍는 메소드
    @AfterThrowing(pointcut = "allControllerPointcut()", throwing = "exception")
    fun writeFailLog(joinPoint: JoinPoint, exception: Exception): Unit {
        val methodName = joinPoint.signature.name
        val className = joinPoint.signature.declaringTypeName
        val args: Array<Any> = joinPoint.args

        val logMessage = "${className}.${methodName}(${Arrays.toString(args)}): ${exception.message}"
    }
}