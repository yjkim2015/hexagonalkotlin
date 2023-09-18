package com.example.demo.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException
import java.time.Instant


class ErrorResponse: RuntimeException {
    var path: String? = null
    var timeStamp: Instant? = null
    var httpStatus: HttpStatus
    var errorMessage: String? = null
    var data: Object? = null

    constructor(errorCode: ErrorCode){
        this.httpStatus = errorCode.getHttpStatus()
        this.errorMessage = errorCode.getErrorMessage()
    }

    constructor(httpStatus: HttpStatus, errorMessage: String, data: Object){
        this.httpStatus = httpStatus
        this.errorMessage = errorMessage
        this.data = data
    }
}