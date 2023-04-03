package com.elicepark.service.game.service.ifs

import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound

/**
 * @author Brian
 * @since 2023/03/29
 */
interface GameService {
    // 경기를 등록하는 메소드
    fun registerGame(createRequest: GameInbound.CreateRequest): GameOutbound.CreateResponse

    // 경기를 N월 M주 필터로 걸러서 페이지네이션 기반으로 가져오는 메소드
    fun getGameListByWeekAndMonth(getRequest: GameInbound.GetGameListOfWeekRequest): List<GameOutbound.GetSimpleResponse>

    // N월 M주에 존재하는 경기의 전체 개수를 반환하는 메소드
    fun getTotalCountByWeekAndMonth(getRequest: GameInbound.GetGameListOfWeekRequest): Int

    // id를 기반으로 경기를 삭제하는 메소드
    fun deleteById(id: Long): GameOutbound.DeleteResponse?
}