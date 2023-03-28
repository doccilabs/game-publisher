package com.elicepark.dto.response

import com.elicepark.domain.entity.Game
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
    ) {
        companion object {
            fun of(game: Game): CreateResponse {
                return with(game) {
                    CreateResponse(
                        this.id,
                        this.teamInfos.homeTeamId,
                        this.teamInfos.homeTeamName,
                        this.teamInfos.awayTeamId,
                        this.teamInfos.awayTeamName,
                        this.startDate
                    )
                }
            }
        }
    }
}