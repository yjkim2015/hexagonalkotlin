package com.example.demo.exception

import org.springframework.http.HttpStatus

interface ErrorCode {
    fun getErrorMessage(): String

    fun getHttpStatus(): HttpStatus
}