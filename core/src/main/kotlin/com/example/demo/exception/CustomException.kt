package com.example.demo.exception

import org.springframework.http.HttpStatus

class CustomException : RuntimeException {
    var status: HttpStatus
    var data: Any?

    constructor(errorCode: ErrorCode, data: Any?){
        this.data = data
        this.status = errorCode.getHttpStatus()
    }
}