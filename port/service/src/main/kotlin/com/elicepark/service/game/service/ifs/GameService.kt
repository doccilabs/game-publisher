package com.elicepark.service.game.service.ifs

import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound

/**
 * @author Brian
 * @since 2023/03/29
 */
interface GameService {
    fun registerGame(createRequest: GameInbound.CreateRequest): GameOutbound.CreateResponse
}