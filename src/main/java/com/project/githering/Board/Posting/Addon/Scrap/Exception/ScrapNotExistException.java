package com.project.githering.Board.Posting.Addon.Scrap.Exception;

import com.project.githering.Common.Exception.RuntimeExceptionWithHttpStatus;
import org.springframework.http.HttpStatus;

public class ScrapNotExistException extends RuntimeExceptionWithHttpStatus {
    public ScrapNotExistException() {
        super("스크랩 정보가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
    }
}
