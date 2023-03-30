package com.elicepark.service.game.service.ifs

import com.elicepark.common.exceptions.GameContinuouslyAssignedException
import com.elicepark.common.exceptions.GameTimeConflictException
import com.elicepark.common.exceptions.InsideFiveDaysException
import com.elicepark.dto.request.GameInbound
import com.elicepark.repository.game.GameRepository
import com.elicepark.service.game.service.GameServiceImpl
import com.elicepark.service.game.validator.GameValidatorImpl
import com.elicepark.service.game.validator.ifs.GameValidator
import io.kotest.assertions.throwables.shouldThrow
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate
import java.time.LocalTime

/**
 * @author Brian
 * @since 2023/03/30
 */
@ExtendWith(MockKExtension::class)
class GameServiceTests {
    private lateinit var gameService: GameService

    private lateinit var gameValidator: GameValidator

    private lateinit var gameRepository: GameRepository

    @BeforeEach
    fun setUp() {
        gameRepository = mockk(relaxUnitFun = true, relaxed = true)
        gameValidator = spyk(GameValidatorImpl(gameRepository))
        gameService = spyk(GameServiceImpl(gameRepository, gameValidator))
    }

    @Test
    fun `현재일로부터 5일 이내에 경기 생성 요청이 된 경우 예외처리한다`() {
        // given
        val createRequest = getCreateRequest(LocalDate.now().plusDays(4), LocalTime.now(), LocalTime.now())

        // then
        shouldThrow<InsideFiveDaysException> { gameService.registerGame(createRequest) }
    }

    @Test
    fun `경기가 이틀 연속으로 잡히도록 스케쥴이 짜여지면 예외처리한다`() {
        // given
        val createRequest = getCreateRequest(LocalDate.now().plusDays(6), LocalTime.now(), LocalTime.now())
        every { gameRepository.isTeamsContinuouslyAssignedBy(createRequest) } returns true

        // then
        shouldThrow<GameContinuouslyAssignedException> { gameService.registerGame(createRequest) }
    }

    @Test
    fun `시간대가 겹치는 경기가 이미 존재하는 경우 예외처리한다`() {
        // given
        val createRequest = getCreateRequest(LocalDate.now().plusDays(6), LocalTime.now(), LocalTime.now())
        every { gameRepository.isTeamsContinuouslyAssignedBy(createRequest) } returns false
        every { gameRepository.isExistsTimeConflictedGameBy(createRequest) } returns true

        // then
        shouldThrow<GameTimeConflictException> { gameService.registerGame(createRequest) }
    }

    @Test
    fun `성공적으로 경기를 등록한다`() {
        // given
        val createRequest = getCreateRequest(LocalDate.now().plusDays(6), LocalTime.now(), LocalTime.now().plusHours(3))
        val createdGame = createRequest.toEntity()

        every { gameRepository.isTeamsContinuouslyAssignedBy(createRequest) } returns false
        every { gameRepository.isExistsTimeConflictedGameBy(createRequest) } returns false
        // JPA에서 save를 호출하면 Any를 반환하는 현상은 이렇게 해결 가능
        every { gameRepository.save(any()) } returns createdGame

        // when
        val createResponse = gameService.registerGame(createRequest)

        // then
        verify(exactly = 1) { gameRepository.isTeamsContinuouslyAssignedBy(createRequest) }
        verify(exactly = 1) { gameRepository.isExistsTimeConflictedGameBy(createRequest) }
        verify(exactly = 1) { gameValidator.validateCreatable(createRequest) }
        verify(exactly = 1) { gameService.registerGame(createRequest) }

        assertEquals(createRequest.awayTeamId, createResponse.teamInfo.awayTeamId)
        assertEquals(createRequest.homeTeamId, createResponse.teamInfo.homeTeamId)
    }

    // createRequest를 생성하는 메소드
    private fun getCreateRequest(
        startDate: LocalDate,
        startTime: LocalTime,
        endTime: LocalTime,
        homeTeamId: String = "1",
        awayTeamId: String = "2"
    ): GameInbound.CreateRequest {
        val homeTeamName = "team${homeTeamId}"
        val awayTeamName = "team${awayTeamId}"

        return GameInbound.CreateRequest(
            homeTeamId,
            homeTeamName,
            awayTeamId,
            awayTeamName,
            startDate,
            startTime,
            endTime
        )
    }
}