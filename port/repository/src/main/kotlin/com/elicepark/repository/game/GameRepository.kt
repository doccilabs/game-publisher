package com.elicepark.repository.game

import com.elicepark.domain.entity.Game
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Brian
 * @since 2023/03/28
 */
interface GameRepository: JpaRepository<Game, Long> {

}