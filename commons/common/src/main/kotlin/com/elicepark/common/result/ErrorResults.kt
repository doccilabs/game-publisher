package com.elicepark.common.result

import com.elicepark.common.error.ErrorCode
import org.springframework.web.bind.support.WebExchangeBindException

/**
 * Error에 대한 Response 인터페이스를 정의하는 sealed class
 * @author Brian
 * @since 2023/03/01
 */
sealed class ErrorResults {

    /**
     * Error에 대한 Response를 담당하는 data class
     * @param error 에러메시지
     */
    data class Response(
        val error: String
    ) {
        companion object {
            // 필드에서 에러가 터진게 아닌 경우
            fun of(errorCode: ErrorCode): Response = Response(errorCode.message)

            // 필드에서 에러가 터진 경우 -> 리스트의 첫번째 원소의 메세지만 뽑아준다
            fun of(fieldErrorList: List<FieldError>): Response = Response(fieldErrorList.first().message)

            // Validation 과정에서 에러가 발생한 경우 -> 첫번째 필드에 대해서만 에러를 내려준다
            fun of(webExchangeBindResult: WebExchangeBindException): Response =
                Response(FieldError.of(webExchangeBindResult).first().message)
        }
    }

    /**
     * Field에 대한 에러를 담아주는 inner class
     * @param field 에러가 발생한 필드의 이름
     * @param value 에러가 발생한 필드가 원래 가지고 있던 값
     * @param message 에러가 발생한 이유
     */
    class FieldError private constructor(val field: String, val value: String, val message: String) {
        companion object {
            // 에러를 일으키는 필드가 하나만 존재하는 경우
            fun of(field: String, value: String, message: String) = FieldError(field, value, message)

            // WebExchangeBindResult를 가진 exception에 대해서 BindResult에 담긴 모든 에러를 반환해주는 메소드
            fun of(webExchangeBindResult: WebExchangeBindException): List<FieldError> {
                val fieldErrorList = webExchangeBindResult.fieldErrors

                return fieldErrorList.map { of(it.field, it.rejectedValue.toString(), it.defaultMessage!!) }
            }
        }
    }
}