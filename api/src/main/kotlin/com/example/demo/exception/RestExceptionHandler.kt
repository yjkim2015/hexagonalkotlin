package com.example.demo.exception

import lombok.extern.slf4j.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
@Slf4j
class RestExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(CustomException::class)
    public fun handleCustomException(request: HttpServletRequest, response: HttpServletResponse, exception: CustomException) : ResponseEntity<Any>{
        logger.error(exception.message, exception)
        var errorResponse = ErrorResponse(exception.status, exception.message, exception.data)
        errorResponse.path = request.servletPath
        errorResponse.timeStamp = Instant.now()
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(Exception::class)
    public fun handleAllException(request: HttpServletRequest, response: HttpServletResponse, exception: Exception) : ResponseEntity<Any>{
        logger.error(exception.message, exception)
        var errorResponse = ErrorResponse(CommonErrorCode.INTERNAL_SERVER_ERROR)
        errorResponse.path = request.servletPath
        errorResponse.timeStamp = Instant.now()
        return buildResponseEntity(errorResponse);
    }

    private fun buildResponseEntity(errorResponse: ErrorResponse) : ResponseEntity<Any>{
        return ResponseEntity(errorResponse as Any, errorResponse.httpStatus)
    }
}