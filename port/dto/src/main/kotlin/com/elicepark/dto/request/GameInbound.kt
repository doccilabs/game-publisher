package com.elicepark.dto.request

import com.elicepark.domain.entity.Game
import com.elicepark.domain.enums.Status
import com.elicepark.domain.vo.TImeInfos
import com.elicepark.domain.vo.TeamInfos
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.time.LocalTime
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
        val startDate: LocalDate,
        @field:NotNull
        val startTime: LocalTime,
        @field:NotNull
        val endTime: LocalTime
    ) {
        // CreateRequest를 Entity로 변환하는 메소드
        fun toEntity(): Game {
            val teamInfos: TeamInfos = TeamInfos(this.homeTeamId, this.homeTeamName, this.awayTeamId, this.awayTeamName)
            val timeInfos = TImeInfos(startDate, startTime, endTime)
            return Game(
                teamInfos = teamInfos,
                timeInfos = timeInfos,
                registerDate = LocalDate.now(),
                status = Status.READY
            )
        }
    }

    // N월M주에 있는 경기를 가져올 때 사용하는 DTO
    data class GetGameListOfWeekRequest(
        val from: LocalDate,
        val to: LocalDate,
        val pageable: Pageable
    ) {

    }
}