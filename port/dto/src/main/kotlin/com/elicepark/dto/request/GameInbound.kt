package com.elicepark.dto.request

import com.elicepark.domain.entity.Game
import com.elicepark.domain.enums.Status
import com.elicepark.domain.vo.TeamInfos
import java.time.LocalDate
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank

/**
 * @author Brian
 * @since 2023/03/28
 */
sealed class GameInbound {
    // 경기 생성 요청을 담당하는 dto
    data class CreateRequest(
        @field:NotBlank
        val homeTeamId: String,
        @field:NotBlank
        val homeTeamName: String,
        @field:NotBlank
        val awayTeamId: String,
        @field:NotBlank
        val awayTeamName: String,
        @field:Future
        val startDate: LocalDate
    ) {
        // CreateRequest를 Entity로 변환하는 메소드
        fun toEntity(): Game {
            val teamInfos: TeamInfos = TeamInfos(this.homeTeamId, this.homeTeamName, this.awayTeamId, this.awayTeamName)
            return Game(
                teamInfos = teamInfos,
                startDate = startDate,
                registerDate = LocalDate.now(),
                status = Status.READY
            )
        }
    }
}