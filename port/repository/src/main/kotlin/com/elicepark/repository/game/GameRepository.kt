package com.elicepark.repository.game

import com.elicepark.domain.entity.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Brian
 * @since 2023/03/28
 */
@Repository
interface GameRepository: JpaRepository<Game, Long> {

}