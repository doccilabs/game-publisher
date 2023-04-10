package com.elicepark.admin.config

import com.elicepark.admin.exceptions.MessagingFailOnceException
import com.elicepark.common.coroutine.BaseCoroutineConfig
import kotlinx.coroutines.*
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.coroutines.CoroutineContext

/**
 * @author Brian
 * @since 2023/04/08
 */
@Configuration
class CoroutineConfig : BaseCoroutineConfig() {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @get:Bean("customIODispatcher")
    @OptIn(ExperimentalCoroutinesApi::class)
    final override val customIODispatcher: CoroutineDispatcher
        get() = Dispatchers.IO.limitedParallelism(80)

    final override fun coroutineExceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            // TODO 보상 트랜잭션 발행하도록 로직 작성
            if (throwable is MessagingFailOnceException) {
                val failMessage = throwable.failedMessage
                logger.info(failMessage.toString())
            }
        }

    // Coroutine Context 정의
    @get:Bean("customCoroutineScope")
    val coroutineContext: CoroutineContext
        get() = supervisorJob + customIODispatcher + coroutineExceptionHandler()

    @Bean
    override fun coroutineScope(): CoroutineScope = CoroutineScope(coroutineContext)
}