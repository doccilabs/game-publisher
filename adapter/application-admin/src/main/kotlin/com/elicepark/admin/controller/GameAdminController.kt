package com.elicepark.admin.controller

import com.elicepark.common.result.ResultFactory
import com.elicepark.common.result.SuccessResults
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.service.game.service.ifs.GameService
import kotlinx.coroutines.coroutineScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
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
            val gameCreatedResponse = gameService.registerGame(createRequest)

            return@coroutineScope ResultFactory.getSingleResponse(gameCreatedResponse)
        }

    // 경기를 삭제하는 메소드
    @DeleteMapping("/{game-id}")
    suspend fun deleteGame(@PathVariable(name = "game-id") gameId: Long): ResponseEntity<SuccessResults.Single<GameOutbound.DeleteResponse?>> =
        coroutineScope {
            val gameDeletedResponse = gameService.deleteById(gameId)

            val responseBuilder = when (gameDeletedResponse) {
                null -> ResponseEntity.status(204)
                else -> ResponseEntity.status(200)
            }

            return@coroutineScope responseBuilder.body(ResultFactory.getSingleResponse(gameDeletedResponse))
        }
}