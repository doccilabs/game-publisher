package com.elicepark.repository.game

import com.elicepark.domain.entity.Game
import com.elicepark.dto.request.GameInbound
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

/**
 * @author Brian
 * @since 2023/03/28
 */
interface GameRepository : JpaRepository<Game, Long>, GameRepositoryCustom {

}