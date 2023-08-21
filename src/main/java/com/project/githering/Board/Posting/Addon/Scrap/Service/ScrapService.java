package com.project.githering.Board.Posting.Addon.Scrap.Service;


import com.project.githering.Board.Posting.Addon.Scrap.Entity.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScrapService {

    //CREATE, UPDATE
    void saveScrap(Long postingId, Long userId);

    //DELETE
    void deleteScrap(Long postingId, Long userId);

    //READ
    Boolean isScrapExist(Long postingId, Long userId);

    Page<Scrap> findAllByUserId(Long userId, Pageable pageable);
}
