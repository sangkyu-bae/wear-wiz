package com.wearwiz.infra.excption;

import com.wearwiz.common.error.ErrorException;
import com.wearwiz.common.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ErrorException.class)
    protected ResponseEntity<?> handleErrorException(ErrorException e){
        log.error("handleCustomException throw CustomException : {}", e.getErrorCode());
        log.error("error method : {}", e.getMethod()+"()");
        return ErrorResponse.toResponseEntity(e.getErrorCode(),e.getMethod()+"()");
    }
}
