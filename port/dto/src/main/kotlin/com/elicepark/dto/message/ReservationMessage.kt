package com.elicepark.dto.message

import java.time.LocalDate

data class ReservationMessage(
    val gameId: Long,
    val reservationAvailableDate: LocalDate,
    val isDeleteRequest: Boolean,
    val failCount: Int
)
