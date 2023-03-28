package com.elicepark.admin.controller

import com.elicepark.common.result.ResultFactory
import com.elicepark.common.result.SuccessResults
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.service.game.GameService
import kotlinx.coroutines.coroutineScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author Brian
 * @since 2023/03/01
 */
@RestController
@RequestMapping("/api/admin/games")
class GameAdminController(private val gameService: GameService) {
    // 경기를 생성하는 메소드
    @PostMapping("")
    suspend fun createGame(@Valid @RequestBody createRequest: GameInbound.CreateRequest): SuccessResults.Single<GameOutbound.CreateResponse> =
        coroutineScope {
            val createResponse = gameService.registerGame(createRequest)

            return@coroutineScope ResultFactory.getSingleResponse(createResponse)
        }
}