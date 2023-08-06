package com.project.githering.Board.BoardCategory.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class BoardCategoryNotExistException extends RuntimeExceptionWithHttpStatus {
    public BoardCategoryNotExistException() {
        super("존재하지 않는 카테고리입니다.", HttpStatus.NOT_FOUND);
    }
}
