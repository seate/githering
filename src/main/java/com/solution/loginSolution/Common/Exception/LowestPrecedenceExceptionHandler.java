package com.solution.loginSolution.Common.Exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class LowestPrecedenceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unHandledError(Exception e) {
        log.error("Unhandled Error", e);
        return new ResponseEntity<>("예상치 못한 에러가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
