package com.elicepark.common.exceptions

import com.elicepark.common.result.ErrorResults

data class InsideFiveDaysException(
    override val message: String = "경기 날짜는 현재 날짜로부터 5일 이후부터 등록 가능합니다",
    val fieldError: ErrorResults.FieldError
) : RuntimeException(message) {

}
