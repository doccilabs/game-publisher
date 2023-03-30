package com.elicepark.common.exceptions

import com.elicepark.common.error.ErrorCode

/**
 * @author Brian
 * @since 2023/03/30
 */
class GameTimeConflictException(val errorCode: ErrorCode): RuntimeException() {

}