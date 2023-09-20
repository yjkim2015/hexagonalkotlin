package com.example.demo.exception

import org.springframework.http.HttpStatus

enum class CommonErrorCode(private val status: HttpStatus, private val message: String) : ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    INVALID_SORT(HttpStatus.BAD_REQUEST, "유효한 정렬 값이 아닙니다"),
    INVALID_PAGE(HttpStatus.BAD_REQUEST, "Page 값은 1부터 입니다."),
    INVALID_SIZE(HttpStatus.BAD_REQUEST, "Size 값은 1부터 입니다.");

    override fun getErrorMessage(): String {
        return this.message
    }

    override fun getHttpStatus(): HttpStatus {
        return this.status
    }
}