package com.project.githering.Board.Posting.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class PostingNotExistException extends RuntimeExceptionWithHttpStatus {
    public PostingNotExistException() {
        super("존재하지 않는 포스팅입니다.", HttpStatus.NOT_FOUND);
    }
}
