package com.elicepark.service.game.service

import com.elicepark.common.error.ErrorCode
import com.elicepark.common.exceptions.GameContinuouslyAssignedException
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.repository.game.GameRepository
import com.elicepark.service.game.service.ifs.GameService
import com.elicepark.service.game.validator.ifs.GameValidator
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * @author Brian
 * @since 2023/03/29
 */
@Service
@Transactional
class GameServiceImpl(
    private val gameRepository: GameRepository,
    private val gameValidator: GameValidator
) : GameService {
    override fun registerGame(createRequest: GameInbound.CreateRequest): GameOutbound.CreateResponse {
        // 게임이 생성 가능한지 검증
        gameValidator.validateCreatable(createRequest)

        // 경기일이 이틀 연속으로 잡힌 경우 예외처리
        val isContinuouslyAssigned = gameRepository.isTeamsContinuouslyAssignedBy(createRequest)

        if(isContinuouslyAssigned) {
            throw GameContinuouslyAssignedException(ErrorCode.GAME_CONTINUOUSLY_ASSIGNED_ERROR)
        }

        // 생성 가능한 경우 경기 등록 진행
        val game = createRequest.toEntity()
        val savedGame = gameRepository.save(game)
        return GameOutbound.CreateResponse.of(savedGame)
    }
}