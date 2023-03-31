package com.elicepark.user.controller

import com.elicepark.common.result.ResultFactory
import com.elicepark.common.result.SuccessResults
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.service.game.service.ifs.GameService
import kotlinx.coroutines.coroutineScope
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author Brian
 * @since 2023/03/31
 */
@RestController
@RequestMapping("/api/user/games")
class GameUserController(
    private val gameService: GameService
) {
    @GetMapping()
    suspend fun getGamesByMonthAndWeek(
        @RequestParam month: Int,
        @RequestParam week: Int,
        @RequestParam page: Int,
        @RequestParam limit: Int
    ): SuccessResults.Paginated<GameOutbound.GetSimpleResponse> = coroutineScope {
        val pageable = PageRequest.of(page, limit)
        val getRequest = GameInbound.GetGameListOfWeekRequest(month, week, pageable)

        val responseBody = with(getRequest) {
            val gameResponseList = gameService.getGameListByWeekAndMonth(this)
            val totalCount = gameService.getTotalCountByWeekAndMonth(this)

            ResultFactory.getPaginatedResponse(totalCount, page, gameResponseList)
        }

        return@coroutineScope responseBody
    }
}