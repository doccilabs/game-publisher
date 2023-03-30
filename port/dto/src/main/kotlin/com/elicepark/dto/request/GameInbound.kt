package com.elicepark.dto.request

import com.elicepark.domain.entity.Game
import com.elicepark.domain.enums.Status
import com.elicepark.domain.vo.TeamInfos
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import javax.validation.constraints.Future
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

/**
 * @author Brian
 * @since 2023/03/28
 */
sealed class GameInbound {
    // 경기 생성 요청을 담당하는 dto
    data class CreateRequest(
        @field:NotNull()
        @field:NotBlank(message = "홈팀의 식별자는 반드시 필요합니다")
        val homeTeamId: String,
        @field:NotNull()
        @field:NotBlank(message = "홈팀의 이름은 반드시 필요합니다")
        val homeTeamName: String,
        @field:NotNull()
        @field:NotBlank(message = "상대팀의 식별자는 반드시 필요합니다")
        val awayTeamId: String,
        @field:NotNull()
        @field:NotBlank(message = "상대팀의 이름은 반드시 필요합니다")
        val awayTeamName: String,
        @field:NotNull()
        @field:Future(message = "과거 시점으로 경기 일정을 잡을 수 없습니다")
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