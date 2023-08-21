package com.project.githering.Board.Posting.Addon.Comment.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class CommentNotExistException extends RuntimeExceptionWithHttpStatus {
    public CommentNotExistException() {
        super("스크랩 정보가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }

    public CommentNotExistException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
