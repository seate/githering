package com.project.githering.Board.Posting.Addon.PostingLike.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class PostingLikeNotExistException extends RuntimeExceptionWithHttpStatus {
    public PostingLikeNotExistException() {
        super("추천정보가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
