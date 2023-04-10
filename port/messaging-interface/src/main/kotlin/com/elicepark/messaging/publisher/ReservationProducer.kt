package com.elicepark.messaging.publisher

import com.amazonaws.services.sqs.model.SendMessageResult
import com.elicepark.dto.message.ReservationMessage

/**
 * 경기 생성 혹은 삭제에 따른 예약 메시지를 발행하는 인터페이스
 * @author Brian
 * @since 2023/04/10
 */
interface ReservationProducer {
    // 경기 생성에 의한 예약 도큐먼트 생성 메시지를 발행하는 메소드
    suspend fun sendCreatedMessage(createdMessage: ReservationMessage): SendMessageResult
}