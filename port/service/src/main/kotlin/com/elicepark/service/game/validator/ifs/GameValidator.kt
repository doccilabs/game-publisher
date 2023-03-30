package com.elicepark.service.game.validator.ifs

import com.elicepark.dto.request.GameInbound

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
interface GameValidator {
    // 해당 경기가 생성 가능한지 여부를 검증하는 메소드
    fun validateCreatable(createRequest: GameInbound.CreateRequest): Unit
}