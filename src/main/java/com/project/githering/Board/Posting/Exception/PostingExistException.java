package com.project.githering.Board.Posting.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class PostingExistException extends RuntimeExceptionWithHttpStatus {
    public PostingExistException() {
        super("이미 존재하는 포스팅입니다.", HttpStatus.CONFLICT);
    }
}
