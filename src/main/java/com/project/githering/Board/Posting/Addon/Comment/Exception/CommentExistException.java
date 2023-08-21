package com.project.githering.Board.Posting.Addon.Comment.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class CommentExistException extends RuntimeExceptionWithHttpStatus {
    public CommentExistException() {
        super("이미 스크랩한 글입니다.", HttpStatus.CONFLICT);
    }
}
