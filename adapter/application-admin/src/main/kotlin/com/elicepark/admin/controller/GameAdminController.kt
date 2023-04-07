package com.elicepark.admin.controller

import com.elicepark.common.result.ResultFactory
import com.elicepark.common.result.SuccessResults
import com.elicepark.dto.request.GameInbound
import com.elicepark.dto.response.GameOutbound
import com.elicepark.service.game.service.ifs.GameService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import kotlin.coroutines.CoroutineContext

/**
 * @author Brian
 * @since 2023/03/01
 * @param gameService 경기에 대한 비지니스 로직을 정리한 service class
 * @param coroutineContext 경기 생성 혹은 삭제 성공 시에 메세지를 발행하는데 사용할 코루틴 컨텍스트
 */
@RestController
@RequestMapping("/api/admin/games")
class GameAdminController(
    private val gameService: GameService,
    private val coroutineContext: CoroutineContext
) {
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