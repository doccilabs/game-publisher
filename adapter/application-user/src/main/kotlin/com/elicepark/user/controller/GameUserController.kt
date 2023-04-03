package com.elicepark.user.controller

import com.elicepark.common.result.ResultFactory
import com.elicepark.common.result.SuccessResults
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.service.game.service.ifs.GameService
import kotlinx.coroutines.coroutineScope
import org.springframework.data.domain.PageRequest
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

/**
 * @author Brian
 * @since 2023/03/31
 */
@RestController
@RequestMapping("/api/user/games")
class GameUserController(
    private val gameService: GameService
) {
    @GetMapping("")
    suspend fun getGamesByMonthAndWeek(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) from: LocalDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) to: LocalDate,
        @RequestParam page: Int,
        @RequestParam limit: Int
    ) = coroutineScope {
        val pageable = PageRequest.of(page - 1, limit)
        val getRequest = GameInbound.GetGameListOfWeekRequest(from, to, pageable)
        
        with(getRequest) {
            val gameResponseList = gameService.getGameListByWeekAndMonth(this)
            val totalCount = gameService.getTotalCountByWeekAndMonth(this)

            if (gameResponseList.isNotEmpty()) {
                return@coroutineScope ResponseEntity.status(200).body(ResultFactory.getPaginatedResponse(totalCount, page, gameResponseList))
            }

            return@coroutineScope ResponseEntity.status(204).body(ResultFactory.getPaginatedResponse(totalCount, page, gameResponseList))
        }
    }
}