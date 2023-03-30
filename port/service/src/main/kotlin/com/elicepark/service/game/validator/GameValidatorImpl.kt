package com.elicepark.service.game.validator

import com.elicepark.service.game.validator.ifs.GameValidator
import com.elicepark.common.exceptions.InsideFiveDaysException
import com.elicepark.common.result.ErrorResults
import com.elicepark.dto.request.GameInbound
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.Period

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
@Component
class GameValidatorImpl : GameValidator {
    override fun validateCreatable(createRequest: GameInbound.CreateRequest) = with(createRequest) {
        val isAfterFiveDays = isAfterFiveDays(this.startDate)

        if (!isAfterFiveDays) {
            val fieldError =
                ErrorResults.FieldError.of("startDate", this.startDate.toString(), "경기 날짜는 등록일로부터 최소 5일 이후부터 가능합니다")
            throw InsideFiveDaysException(fieldError = fieldError)
        }
    }

    // 5일 이후의 날짜인지 판단하는 메소드
    private fun isAfterFiveDays(givenDate: LocalDate): Boolean {
        val currentDate = LocalDate.now()
        val betweenDays = Period.between(currentDate, givenDate).days

        return betweenDays >= 5
    }
}