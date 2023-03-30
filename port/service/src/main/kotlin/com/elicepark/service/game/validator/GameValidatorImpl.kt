package com.elicepark.service.game.validator

import com.elicepark.common.error.ErrorCode
import com.elicepark.common.exceptions.GameContinuouslyAssignedException
import com.elicepark.common.exceptions.GameTimeConflictException
import com.elicepark.service.game.validator.ifs.GameValidator
import com.elicepark.common.exceptions.InsideFiveDaysException
import com.elicepark.common.result.ErrorResults
import com.elicepark.dto.request.GameInbound
import com.elicepark.repository.game.GameRepository
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
@Component
class GameValidatorImpl(
    private val gameRepository: GameRepository
) : GameValidator {
    override fun validateCreatable(createRequest: GameInbound.CreateRequest) = with(createRequest) {
        // 현재 일자로부터 5일 이후가 아닌 경우에 에러를 발생시킨다
        isAfterFiveDays(this.startDate)

        // 경기가 이틀 연속으로 잡히는 경우 예외처리
        isGameContinuouslyAssigned(this)

        // 겹치는 시간대의 경기가 존재하는 경우도 예외처리
        isExistsTimeConflictedGame(this)
    }

    // 5일 이후의 날짜인지 판단하는 메소드
    private fun isAfterFiveDays(givenDate: LocalDate) {
        val currentDate = LocalDate.now()
        val betweenDays = Period.between(currentDate, givenDate).days

        if (betweenDays < 5) {
            val fieldError =
                ErrorResults.FieldError.of("startDate", givenDate.toString(), "경기 날짜는 등록일로부터 최소 5일 이후부터 가능합니다")
            throw InsideFiveDaysException(fieldError = fieldError)
        }
    }

    // 경기가 이틀 연속으로 잡혀있는 경우 예외처리를 하는 메소드
    private fun isGameContinuouslyAssigned(createRequest: GameInbound.CreateRequest) {
        // 경기일이 이틀 연속으로 잡힌 경우 예외처리
        val isContinuouslyAssigned = gameRepository.isTeamsContinuouslyAssignedBy(createRequest)

        if(isContinuouslyAssigned) {
            throw GameContinuouslyAssignedException(ErrorCode.GAME_CONTINUOUSLY_ASSIGNED_ERROR)
        }
    }

    private fun isExistsTimeConflictedGame(createRequest: GameInbound.CreateRequest) {
        val isExistsTimeConflictedGame = gameRepository.isExistsTimeConflictedGameBy(createRequest)

        if(isExistsTimeConflictedGame) {
            throw GameTimeConflictException(ErrorCode.GAME_TIME_CONFLICT_ERROR)
        }
    }
}