package com.elicepark.dto.response

import java.time.LocalDate

/**
 * @author Brian
 * @since 2023/03/28
 */
sealed class GameOutbound {
    data class CreateResponse(
        val id: Long,
        val homeTeamId: String,
        val homeTeamName: String,
        val awayTeamId: String,
        val awayTeamName: String,
        val startDate: LocalDate
    )
}