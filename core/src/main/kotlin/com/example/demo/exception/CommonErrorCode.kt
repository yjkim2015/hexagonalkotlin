package com.example.demo.exception

import org.springframework.http.HttpStatus

enum class CommonErrorCode(private val status: HttpStatus, private val message: String) : ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");

    override fun getErrorMessage(): String {
        TODO("Not yet implemented")
    }

    override fun getHttpStatus(): HttpStatus {
        TODO("Not yet implemented")
    }
}