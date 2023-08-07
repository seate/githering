package com.project.githering.Board.Posting.Service;

import com.project.githering.Board.BoardCategory.Service.BoardCategoryService;
import com.project.githering.Board.Posting.DTO.CreatePostingRequestDTO;
import com.project.githering.Board.Posting.DTO.DetailPostingInformResponseDTO;
import com.project.githering.Board.Posting.DTO.SimplePostingInformResponseDTO;
import com.project.githering.Board.Posting.DTO.UpdatePostingRequestDTO;
import com.project.githering.Board.Posting.Entity.Posting;
import com.project.githering.Board.Posting.Exception.PostingNotExistException;
import com.project.githering.Board.Posting.Repository.PostingRepository;
import com.project.githering.Group.Belong.Service.GroupBelongService;
import com.project.githering.Group.Exception.NoAuthorityException;
import com.project.githering.User.General.Exception.UserNotExistException;
import com.project.githering.User.General.Service.GeneralUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;

    private final GroupBelongService groupBelongService;

    private final GeneralUserService generalUserService;

    private final BoardCategoryService boardCategoryService;

    @Override
    @Transactional
    public void savePosting(Long userId, CreatePostingRequestDTO createPostingRequestDTO) {
        Long groupId = createPostingRequestDTO.getGroupId();
        if (!groupBelongService.isJoined(userId, groupId))
            throw new NoAuthorityException("그룹에 가입해야 글을 작성할 수 있습니다.");

        Posting posting = createPostingRequestDTO.toEntity(userId);

        postingRepository.save(posting);
    }

    @Override
    @Transactional
    public void deletePosting(Long userId, Long postingId) {
        Posting posting = findPostingById(postingId);
        if (!posting.getUserId().equals(userId))
            throw new NoAuthorityException("글 작성자만 삭제할 수 있습니다.");

        postingRepository.delete(posting);
    }

    @Override
    @Transactional
    public Posting findPostingById(Long postingId) {
        Posting posting = postingRepository.findById(postingId).orElseThrow(PostingNotExistException::new);
        posting.setViewCount(posting.getViewCount() + 1); // 조회수 증가
        return posting;
    }

    @Override
    public DetailPostingInformResponseDTO findDetailPostingInformById(Long postingId) {
        Posting posting = findPostingById(postingId);
        String userName = generalUserService.findById(posting.getUserId())
                .orElseThrow(UserNotExistException::new).getUserName();
        String categoryName = boardCategoryService.findCategoryNameByCategoryId(posting.getCategoryId());

        return new DetailPostingInformResponseDTO(categoryName, userName, posting);
    }

    @Override
    public Page<Posting> findAllPostingByGroupId(Long groupId, Pageable pageable) {
        return postingRepository.findAllByGroupId(groupId, pageable);
    }

    @Override
    public Page<SimplePostingInformResponseDTO> findAllSimplePostingInformByCategoryId(Long userId, Long categoryId, Pageable pageable) {
        Page<Posting> allPostingByCategoryId = findAllPostingByCategoryId(categoryId, pageable);

        String userName = generalUserService.findById(userId).orElseThrow(UserNotExistException::new).getUserName();
        String categoryName = boardCategoryService.findCategoryNameByCategoryId(categoryId);

        return allPostingByCategoryId.map(
                posting -> new SimplePostingInformResponseDTO(userName, categoryName, posting)
        );
    }

    private Page<Posting> findAllPostingByCategoryId(Long categoryId, Pageable pageable) {
        return postingRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Posting> findAllPostingByUserId(Long userId, Pageable pageable) {
        return postingRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public void updatePosting(Long userId, Long postingId, UpdatePostingRequestDTO updatePostingRequestDTO) {
        Posting posting = findPostingById(postingId);
        if (!posting.getUserId().equals(userId))
            throw new NoAuthorityException("글 작성자만 수정할 수 있습니다.");

        posting.setCategoryId(updatePostingRequestDTO.getCategoryId());
        posting.setTitle(updatePostingRequestDTO.getTitle());
        posting.setContent(updatePostingRequestDTO.getContent());
    }
}
