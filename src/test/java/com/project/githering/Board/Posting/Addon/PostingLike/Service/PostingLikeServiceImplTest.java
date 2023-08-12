package com.project.githering.Board.Posting.Addon.PostingLike.Service;

import com.project.githering.Board.Posting.Addon.PostingLike.Exception.PostingLikeExistException;
import com.project.githering.Board.Posting.Service.PostingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostingLikeServiceImplTest {

    @Autowired
    private PostingService postingService;

    @Test
    void like() {
        postingService.updateLike(1L, 1L);

        Assertions.assertThrows(PostingLikeExistException.class,
                () -> postingService.updateLike(1L, 1L)
        );




    }

}