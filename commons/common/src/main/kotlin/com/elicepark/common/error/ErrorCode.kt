package com.elicepark.common.error

/**
 * @author Brian
 * @since 2023/03/01
 */
enum class ErrorCode(
    val status: Int,
    val message: String
) {
    INPUT_ERROR(400, "입력값이 올바르지 않습니다"),
    ARGUMENT_ERROR(400, "입력값이 비어있거나 없습니다"),
    REQUEST_VALIDATION_ERROR(400, "입력값이 유효하지 않습니다"),
    RESOURCE_DUPLICATION_ERROR(400, "이미 존재합니다"),
    AUTHENTICATION_ERROR(401, "허용되지 않은 접근입니다"),
    AUTHORIZATION_ERROR(403, "올바르지 않은 권한입니다"),
    RESOURCE_NOT_FOUND_ERROR(404, "리소스가 존재하지 않습니다"),

    /* 커스텀 오류 */
    GAME_CONTINUOUSLY_ASSIGNED_ERROR(400, "이틀 연속으로 경기가 잡히는 팀이 존재합니다. 다시 확인해주세요"),
    GAME_TIME_CONFLICT_ERROR(400, "시간대가 겹치는 경기가 존재합니다. 다시 확인해주세요")
}