package com.elicepark.common.result

import com.elicepark.common.error.ErrorCode
import org.springframework.web.bind.support.WebExchangeBindException

/**
 * 결과 반환에 대한 메소드들을 정의하는 Factory class
 * @author Brian
 * @since 2023/03/01
 */
object ResultFactory {
    // 성공에 대한 결과를 리턴하는 메소드
    fun getSuccessResponse(): SuccessResults.Single<String> = SuccessResults.Single("OK")

    // 단일 데이터를 가지는 성공 결과를 반환하는 메소드
    fun <T> getSingleResponse(data: T): SuccessResults.Single<T> = SuccessResults.Single(data)

    // 여러개 데이터를 성공 결과로 가지는 경우를 반환하는 메소드
    fun <T> getMultipleResponse(data: List<T>): SuccessResults.Multiple<T> = SuccessResults.Multiple(data)

    // 페이지네이션 된 데이터를 성공 결과로 가지는 경우를 반환하는 메소드
    fun <T> getPaginatedResponse(
        totalElements: Int,
        page: Int,
        data: List<T>
    ): SuccessResults.Paginated<T> {
        val totalPages = Math.ceil(totalElements.toDouble() / data.size.toDouble()).toInt()

        return SuccessResults.Paginated(totalPages, totalElements, page, data.size, data)
    }

    // field에서 에러가 터지지 않은 경우 에러코드만 포함시켜서 반환해주는 메소드
    fun getErrorResponse(errorCode: ErrorCode): ErrorResults.Response = ErrorResults.Response.of(errorCode)

    // field에서 에러가 발생하였으나, validation 과정에서 걸린게 아닌 경우
    fun getFieldErrorResponse(fieldErrorList: List<ErrorResults.FieldError>): ErrorResults.Response =
        ErrorResults.Response.of(fieldErrorList)

    // field에서 에러가 발생하였으나, validation 과정에서 걸린 경우
    fun getFieldErrorResponse(webExchangeBindException: WebExchangeBindException): ErrorResults.Response =
        ErrorResults.Response.of(webExchangeBindException)
}