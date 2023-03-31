package com.elicepark.dto.response

import com.elicepark.domain.entity.Game
import com.elicepark.domain.enums.Status
import com.elicepark.domain.vo.TImeInfos
import com.elicepark.domain.vo.TeamInfos
import java.time.LocalDate

/**
 * @author Brian
 * @since 2023/03/28
 */
sealed class GameOutbound {
    // 생성 요청에 대한 응답
    data class CreateResponse(
        val id: Long,
        val teamInfo: TeamInfos,
        val timeInfo: TImeInfos
    ) {
        companion object {
            fun of(game: Game): CreateResponse = with(game) {
                CreateResponse(this.id, this.teamInfos, this.timeInfos)
            }
        }
    }

    // 간단한 정보를 조회하는 경우의 DTO
    data class GetSimpleResponse(
        val id: Long,
        val homeTeamId: String,
        val awayTeamId: String,
        val timeInfo: TImeInfos,
        val status: Status
    ) {
        companion object {
            fun of(game: Game): GetSimpleResponse = with(game) {
                GetSimpleResponse(
                    this.id,
                    this.teamInfos.homeTeamId,
                    this.teamInfos.awayTeamId,
                    this.timeInfos,
                    this.status
                )
            }
        }
    }
}