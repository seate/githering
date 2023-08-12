package com.project.githering.Board.Posting.Addon.PostingLike.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class PostingLikeExistException extends RuntimeExceptionWithHttpStatus {
    public PostingLikeExistException() {
        super("이미 추천/비추천했습니다.", HttpStatus.CONFLICT);
    }
}
