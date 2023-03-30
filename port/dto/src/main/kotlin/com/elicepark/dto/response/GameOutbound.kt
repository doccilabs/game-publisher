package com.elicepark.dto.response

import com.elicepark.domain.entity.Game
import com.elicepark.domain.vo.TImeInfos
import com.elicepark.domain.vo.TeamInfos
import java.time.LocalDate

/**
 * @author Brian
 * @since 2023/03/28
 */
sealed class GameOutbound {
    data class CreateResponse(
        val id: Long,
        val teamInfo: TeamInfos,
        val timeInfo: TImeInfos
    ) {
        companion object {
            fun of(game: Game): CreateResponse {
                return with(game) {
                    CreateResponse(this.id, this.teamInfos, this.timeInfos)
                }
            }
        }
    }
}