package com.elicepark.common.exceptions

import com.elicepark.common.error.ErrorCode

// 경기가 이틀 연속으로 잡힌 경우에 발생시키는 에러
data class GameContinuouslyAssignedException(val errorCode: ErrorCode) : RuntimeException()
