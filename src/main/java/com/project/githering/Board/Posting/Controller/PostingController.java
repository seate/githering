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

    @PostMapping
    public ResponseEntity<Void> createPosting(@RequestBody @Valid CreatePostingRequestDTO createPostingRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        postingService.savePosting(userId, createPostingRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{postingId}")
    public ResponseEntity<Void> deletePosting(@PathVariable Long postingId) {
        Long userId = generalUserService.findIdByAuthentication();
        postingService.deletePosting(userId, postingId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SimplePostingInformResponseDTO>> findAllPostingByGroupId(@RequestParam Long categoryId, Pageable pageable) {
        Long userId = generalUserService.findIdByAuthentication();
        List<SimplePostingInformResponseDTO> SimplePostingList = postingService
                .findAllSimplePostingInformByCategoryId(userId, categoryId, pageable).getContent();
        return new ResponseEntity<>(SimplePostingList, HttpStatus.OK);
    }

    @GetMapping("/{postingId}")
    public ResponseEntity<DetailPostingInformResponseDTO> findPosting(@PathVariable Long postingId) {
        return new ResponseEntity<>(postingService.findDetailPostingInformById(postingId), HttpStatus.OK);
    }

    @PatchMapping("/{postingId}")
    public ResponseEntity<Void> updatePosting(@PathVariable Long postingId, @RequestBody @Valid UpdatePostingRequestDTO updatePostingRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        postingService.updatePosting(userId, postingId, updatePostingRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

