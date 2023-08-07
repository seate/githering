package com.project.githering.Board.Posting.Service;

import com.project.githering.Board.Posting.DTO.CreatePostingRequestDTO;
import com.project.githering.Board.Posting.DTO.DetailPostingInformResponseDTO;
import com.project.githering.Board.Posting.DTO.SimplePostingInformResponseDTO;
import com.project.githering.Board.Posting.DTO.UpdatePostingRequestDTO;
import com.project.githering.Board.Posting.Entity.Posting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostingService {

    //CREATE
    void savePosting(Long userId, CreatePostingRequestDTO createPostingRequestDTO);

    //DELETE
    void deletePosting(Long userId, Long postingId);

    //READ
    Posting findPostingById(Long postingId);

    DetailPostingInformResponseDTO findDetailPostingInformById(Long postingId);

    Page<Posting> findAllPostingByGroupId(Long groupId, Pageable pageable);

    Page<SimplePostingInformResponseDTO> findAllSimplePostingInformByCategoryId(Long userId, Long categoryId, Pageable pageable);

    Page<Posting> findAllPostingByUserId(Long userId, Pageable pageable);

    //UPDATE
    void updatePosting(Long userId, Long postingId, UpdatePostingRequestDTO updatePostingRequestDTO);
}