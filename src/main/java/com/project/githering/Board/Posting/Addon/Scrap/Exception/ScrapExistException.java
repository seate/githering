package com.project.githering.Board.Posting.Addon.Scrap.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ScrapExistException extends RuntimeExceptionWithHttpStatus {
    public ScrapExistException() {
        super("이미 스크랩한 글입니다.", HttpStatus.CONFLICT);
    }
}
