package com.project.githering.Board.Posting.Addon.Scrap.Service;

import com.project.githering.Board.Posting.Addon.Scrap.Entity.Scrap;
import com.project.githering.Board.Posting.Addon.Scrap.Entity.ScrapPk;
import com.project.githering.Board.Posting.Addon.Scrap.Exception.ScrapExistException;
import com.project.githering.Board.Posting.Addon.Scrap.Exception.ScrapNotExistException;
import com.project.githering.Board.Posting.Addon.Scrap.Repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService {

    private final ScrapRepository scrapRepository;

    @Override
    public void saveScrap(Long postingId, Long userId) {
        if (isScrapExist(postingId, userId)) throw new ScrapExistException();

        scrapRepository.save(new Scrap(postingId, userId));
    }

    @Override
    public void deleteScrap(Long postingId, Long userId) {
        if (!isScrapExist(postingId, userId)) throw new ScrapNotExistException();

        scrapRepository.deleteById(new ScrapPk(postingId, userId));
    }

    @Override
    public Boolean isScrapExist(Long postingId, Long userId) {
        return scrapRepository.existsById(new ScrapPk(postingId, userId));
    }

    @Override
    public Page<Scrap> findAllByUserId(Long userId, Pageable pageable) {
        return scrapRepository.findAllByUserId(userId, pageable);
    }
}
