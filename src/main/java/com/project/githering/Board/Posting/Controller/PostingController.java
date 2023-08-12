package com.project.githering.Board.Posting.Controller;

import com.project.githering.Board.Posting.DTO.CreatePostingRequestDTO;
import com.project.githering.Board.Posting.DTO.DetailPostingInformResponseDTO;
import com.project.githering.Board.Posting.DTO.SimplePostingInformResponseDTO;
import com.project.githering.Board.Posting.DTO.UpdatePostingRequestDTO;
import com.project.githering.Board.Posting.Service.PostingService;
import com.project.githering.User.General.Service.GeneralUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/posting")
public class PostingController {

    private final PostingService postingService;

    private final GeneralUserService generalUserService;

    //CREATE
    @PostMapping
    public ResponseEntity<Long> createPosting(@RequestBody @Valid CreatePostingRequestDTO createPostingRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        return new ResponseEntity<>(postingService.savePosting(userId, createPostingRequestDTO), HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("/{postingId}")
    public ResponseEntity<Void> deletePosting(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        postingService.deletePosting(userId, postingId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //READ
    @GetMapping("/{postingId}")
    public ResponseEntity<DetailPostingInformResponseDTO> findPosting(@PathVariable Long postingId) {
        return new ResponseEntity<>(postingService.findDetailPostingInformById(postingId), HttpStatus.OK);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<SimplePostingInformResponseDTO>> findAllByGroupId(@PathVariable Long groupId, Pageable pageable) {
        return new ResponseEntity<>(postingService.findAllPostingByGroupId(groupId, pageable).getContent(), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SimplePostingInformResponseDTO>> findAllByCategoryId(@PathVariable Long categoryId, Pageable pageable) {
        Long userId = generalUserService.findIdByAuthentication();
        List<SimplePostingInformResponseDTO> SimplePostingList = postingService
                .findAllSimplePostingInformByCategoryId(userId, categoryId, pageable).getContent();
        return new ResponseEntity<>(SimplePostingList, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SimplePostingInformResponseDTO>> findAllByUserId(@PathVariable Long userId, Pageable pageable) {
        return new ResponseEntity<>(postingService.findAllPostingByUserId(userId, pageable).getContent(), HttpStatus.OK);
    }


    //UPDATE
    @PatchMapping("/{postingId}/like")
    public ResponseEntity<Void> updateLike(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        postingService.updateLike(userId, postingId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{postingId}/dislike")
    public ResponseEntity<Void> updateDislike(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        postingService.updateDislike(userId, postingId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{postingId}")
    public ResponseEntity<Void> updatePosting(@PathVariable Long postingId, @RequestBody @Valid UpdatePostingRequestDTO updatePostingRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        postingService.updatePosting(userId, postingId, updatePostingRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

