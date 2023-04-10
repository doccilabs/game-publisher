package com.elicepark.producer.publisher

import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.amazonaws.services.sqs.model.SendMessageResult
import com.elicepark.dto.message.ReservationMessage
import com.elicepark.messaging.constants.MessagingConstants
import com.elicepark.messaging.publisher.ReservationProducer
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.UUID
import java.util.concurrent.CompletableFuture

/**
 * @author Brian
 * @since 2023/04/10
 */
@Component
class ReservationProducerImpl(
    @Value("\${cloud.aws.messaging.sqs.queue.url}") private val queueUrl: String,
    private val objectMapper: ObjectMapper,
    private val sqsAsyncClient: AmazonSQSAsync
) : ReservationProducer {

    override suspend fun sendCreatedMessage(createdMessage: ReservationMessage): SendMessageResult {
        val messageBody = objectMapper.writeValueAsString(createdMessage)
        val randomUuid = UUID.randomUUID().toString()

        val request = SendMessageRequest()
            .withQueueUrl(queueUrl)
            .withMessageBody(messageBody)
            .withMessageGroupId(MessagingConstants.RESERVATION_CREATION_ID)
            .withMessageDeduplicationId(randomUuid)

        val completableFuture = CompletableFuture.supplyAsync { sqsAsyncClient.sendMessageAsync(request).get() }

        return Mono.fromCompletionStage(completableFuture)
            .awaitSingle()
    }
}