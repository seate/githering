package com.project.githering.Board.Posting.Addon.PostingLike.Controller;

import com.project.githering.Board.Posting.Addon.PostingLike.Service.PostingLikeService;
import com.project.githering.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posting-like")
public class PostingLikeController {

    private final PostingLikeService postingLikeService;

    private final GeneralUserService generalUserService;

    @PatchMapping("/{postingId}/like")
    public ResponseEntity<Void> updateLike(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        postingLikeService.saveLike(postingId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{postingId}/dislike")
    public ResponseEntity<Void> updateDislike(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        postingLikeService.saveDislike(postingId, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}