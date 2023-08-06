package com.project.githering.Board.BoardCategory.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class BoardCategoryExistException extends RuntimeExceptionWithHttpStatus {
    public BoardCategoryExistException() {
        super("이미 존재하는 카테고리입니다.", HttpStatus.CONFLICT);
    }

    public BoardCategoryExistException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
