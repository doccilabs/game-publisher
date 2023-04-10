package com.elicepark.common.coroutine

import kotlinx.coroutines.*

/**
 * 코루틴 스펙을 정의하는 인터페이스
 * @author Brian
 * @since 2023/04/08
 */
abstract class BaseCoroutineConfig {
    val supervisorJob = SupervisorJob()

    abstract val customIODispatcher: CoroutineDispatcher

    abstract fun coroutineExceptionHandler(): CoroutineExceptionHandler

    abstract fun coroutineScope(): CoroutineScope
}