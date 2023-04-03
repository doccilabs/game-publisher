package com.elicepark.common.result

/** Result의 공통 반환 형태를 정의하는 sealed class
 * @author Brian
 * @since 2023/03/01
 */
sealed class SuccessResults {

    // 단일 데이터에 대한 성공 응답 정의
    data class Single<T>(
        val data: T
    )

    // 복수개의 데이터에 대한 성공 응답 정의
    data class Multiple<T>(
        val data: List<T>
    )

    // 페이지네이션 된 성공 응답 정의
    data class Paginated<T>(
        val totalPages: Int,
        val totalElements: Int,
        val page: Int,
        val elements: Int,
        val data: List<T>
    )
}