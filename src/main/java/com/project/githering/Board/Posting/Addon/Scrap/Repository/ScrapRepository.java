package com.project.githering.Board.Posting.Addon.Scrap.Repository;

import com.project.githering.Board.Posting.Addon.Scrap.Entity.Scrap;
import com.project.githering.Board.Posting.Addon.Scrap.Entity.ScrapPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, ScrapPk> {

    //CREATE

    //DELETE

    //READ
    Page<Scrap> findAllByUserId(Long userId, Pageable pageable);
}
