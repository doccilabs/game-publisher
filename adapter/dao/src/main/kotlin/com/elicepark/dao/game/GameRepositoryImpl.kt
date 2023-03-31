package com.elicepark.dao.game

import com.elicepark.domain.entity.Game
import com.elicepark.domain.entity.QGame.game
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.repository.game.GameRepositoryCustom
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

/**
 * @author Doyeop Kim
 * @since 2023/03/30
 */
@Component
class GameRepositoryImpl(private val queryFactory: JPAQueryFactory) : GameRepositoryCustom {
    override fun isTeamsContinuouslyAssignedBy(createRequest: GameInbound.CreateRequest): Boolean {
        // 홈팀이 새로운 경기 날짜로부터 1일 이내에 경기가 잡혀있는 경우를 뽑아내기
        val gameInsideOneDay = queryFactory.selectFrom(game)
            .where(
                game.teamInfos.homeTeamId.`in`(createRequest.homeTeamId, createRequest.awayTeamId)
                    .or(game.teamInfos.awayTeamId.`in`(createRequest.homeTeamId, createRequest.awayTeamId))
            )
            .where(
                game.timeInfos.startDate.between(
                    createRequest.startDate.minusDays(1),
                    createRequest.startDate.plusDays(1)
                )
            )
            .where(game.deleted.eq(false))
            .limit(1)
            .fetchOne()

        return gameInsideOneDay != null
    }

    override fun isExistsTimeConflictedGameBy(createRequest: GameInbound.CreateRequest): Boolean {
        // 요청 시간에 겹쳐있는 경기 1개를 쿼리해온다
        val timeConflictedGame = queryFactory.selectFrom(game)
            .where(
                game.timeInfos.startTime.loe(createRequest.endTime)
                    .and(game.timeInfos.endTime.goe(createRequest.startTime))
                    .and(game.timeInfos.startDate.eq(createRequest.startDate))
            )
            .limit(1)
            .fetchOne()

        return timeConflictedGame != null
    }

    override fun getGameListWithInByPagination(getRequest: GameInbound.GetGameListOfWeekRequest): List<GameOutbound.GetSimpleResponse> {
        val gameResponseList = queryFactory.select(
            Projections.fields(
                GameOutbound.GetSimpleResponse::class.java,
                game.id,
                game.teamInfos.homeTeamId,
                game.teamInfos.awayTeamId,
                game.timeInfos,
                game.status
            )
        )
            .where(
                game.timeInfos.startDate.between(getRequest.getFirstDate(), getRequest.getLastDate())
            )
            .offset(getRequest.pageable.offset)
            .limit(getRequest.pageable.pageSize.toLong())
            .fetch()


        return gameResponseList
    }

    override fun getCountWithIn(getRequest: GameInbound.GetGameListOfWeekRequest): Int {
        return queryFactory.select(game.id)
            .from(game)
            .where(
                game.timeInfos.startDate.between(getRequest.getFirstDate(), getRequest.getLastDate())
            )
            .fetch()
            .size
    }
}