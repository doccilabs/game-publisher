package com.elicepark.messaging.constants

/**메시징 처리에 사용할 상수들을 정의하는 오브젝트 클래스
 * 그룹 ID 컨벤션: {application}-{module}-{taskType}
 */
object MessagingConstants {
    // 예약 생성 이벤트를 처리하는 그룹 ID
    final val RESERVATION_CREATION_ID = "game-messaging-reservationCreation"
}