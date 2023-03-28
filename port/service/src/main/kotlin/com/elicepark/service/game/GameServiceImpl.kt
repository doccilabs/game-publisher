package com.elicepark.service.game

import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.repository.game.GameRepository
import org.springframework.stereotype.Service

/**
 * @author Brian
 * @since 2023/03/29
 */
@Service
class GameServiceImpl(private val gameRepository: GameRepository) : GameService {
    override fun registerGame(createRequest: GameInbound.CreateRequest): GameOutbound.CreateResponse {
        val game = createRequest.toEntity()
        val savedGame = gameRepository.save(game)
        return GameOutbound.CreateResponse.of(savedGame)
    }
}