package com.project.githering.Board.Posting.Addon.PostingLike.Service;

import com.project.githering.Board.Posting.Addon.PostingLike.Exception.PostingLikeExistException;
import com.project.githering.Board.Posting.Addon.PostingLike.Repository.PostingLikeRepository;
import com.project.githering.Board.Posting.Addon.PostingLike.Entity.PostingLike;
import com.project.githering.Board.Posting.Service.PostingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostingLikeServiceImpl implements PostingLikeService {

    private final PostingLikeRepository postingLikeRepository;

    private final PostingService postingService;


    private void saveOrUpdatePostingLike(Long postingId, Long userId, Boolean isLike) throws PostingLikeExistException {
        postingLikeRepository.findByPostingIdAndUserId(postingId, userId)
                .ifPresentOrElse(
                        postingLike -> {
                            if (postingLike.getIsLikedDisliked() == isLike)
                                throw new PostingLikeExistException();

                            postingService.updateLikeCount(postingId, isLike);
                            postingService.updateDisLikeCount(postingId, !isLike);

                            postingLike.setIsLikedDisliked(isLike);
                            postingLikeRepository.save(postingLike);
                        },
                        () -> {
                            if (isLike) postingService.updateLikeCount(postingId, true);
                            else postingService.updateDisLikeCount(postingId, true);

                            postingLikeRepository.save(new PostingLike(postingId, userId, isLike));
                        }
                );
    }

    @Override
    @Transactional
    public void saveLike(Long postingId, Long userId) {
        saveOrUpdatePostingLike(postingId, userId, true);
    }

    @Override
    @Transactional
    public void saveDislike(Long postingId, Long userId) {
        saveOrUpdatePostingLike(postingId, userId, false);
    }

    @Override
    @Transactional
    public void deletePostingLike(Long postingId, Long userId) {
        postingLikeRepository.deleteByPostingIdAndUserId(postingId, userId);
    }

    public Optional<PostingLike> findByPostingIdAndUserId(Long postingId, Long userId) {
        return postingLikeRepository.findByPostingIdAndUserId(postingId, userId);
    }


    @Override
    public Integer countPostingLike(Long postingId) {
        return postingLikeRepository.countByPostingIdAndIsLikedDisliked(postingId, true);
    }

    @Override
    public Integer countPostingDislike(Long postingId) {
        return postingLikeRepository.countByPostingIdAndIsLikedDisliked(postingId, false);
    }
}
