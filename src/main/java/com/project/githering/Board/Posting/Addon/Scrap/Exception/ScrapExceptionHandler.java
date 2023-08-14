package com.project.githering.Board.Posting.Addon.Scrap.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ScrapExceptionHandler {

    @ExceptionHandler(ScrapExistException.class)
    public ResponseEntity<String> scrapExist(ScrapExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

    @ExceptionHandler(ScrapNotExistException.class)
    public ResponseEntity<String> scrapNotExist(ScrapNotExistException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }
}
