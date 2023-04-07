package com.elicepark.admin.exceptions

import com.elicepark.dto.message.ReservationMessage

/**
 * SQS에 메시지 전송이 최초로 실패 발생 시에 일어나는 예외
 * @author Brian
 * @since 2023/04/08
 */
class MessagingFailOnceException(val failedMessage: ReservationMessage): Throwable() {
}