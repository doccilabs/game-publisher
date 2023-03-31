package com.elicepark.repository.game

import com.elicepark.domain.entity.Game
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
interface GameRepositoryCustom {
    // homeTeam, awayTeam이 이틀 연속으로 경기하는지 여부를 판단해서 반환하는 메소드
    fun isTeamsContinuouslyAssignedBy(createRequest: GameInbound.CreateRequest): Boolean

    // 플레이타임이 겹치는 경기가 존재하는지 여부를 반환하는 메소드
    fun isExistsTimeConflictedGameBy(createRequest: GameInbound.CreateRequest): Boolean

    // 특정 날짜 범위에 해당하는 경기 목록을 페이지네이션으로 가져오는 메소드
    fun getGameListWithInByPagination(getRequest: GameInbound.GetGameListOfWeekRequest): List<GameOutbound.GetSimpleResponse>

    // 특정 날짜 범위에 해당하는 경기의 전체 개수를 가져오는 메소드
    fun getCountWithIn(getRequest: GameInbound.GetGameListOfWeekRequest): Int
}