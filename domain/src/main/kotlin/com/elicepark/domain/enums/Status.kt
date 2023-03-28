package com.elicepark.domain.enums

/**
 * @author Brian
 * @since 2023/03/28
 */
enum class Status(val message: String) {
    READY("준비중"),
    RESERVING("예매 중"),
    RESERVE_FINISHED("예매 마감"),
    GAME_ON_PROCESSING("경기 진행중"),
    GAME_FINISHED("경기 완료"),
    GAME_CANCELLED("취소됨")
}