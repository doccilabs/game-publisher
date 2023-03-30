package com.elicepark.domain.vo

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 * @author Brian
 * @since 2023/03/30
 */
@Embeddable
data class TImeInfos(
    @get:Column(name = "start_date", nullable = false)
    var startDate: LocalDate = LocalDate.now(),
    @get:Column(name = "start_time", nullable = false)
    var startTime: LocalTime = LocalTime.now(),
    @get:Column(name = "end_time", nullable = false)
    var endTime: LocalTime = LocalTime.now()
) {
}