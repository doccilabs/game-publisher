package com.elicepark.repository.game

import com.elicepark.dto.request.GameInbound

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
interface GameRepositoryCustom {
    // homeTeam, awayTeam이 이틀 연속으로 경기하는지 여부를 판단해서 반환하는 메소드
    fun isTeamsContinuouslyAssignedBy(createRequest: GameInbound.CreateRequest): Boolean
}