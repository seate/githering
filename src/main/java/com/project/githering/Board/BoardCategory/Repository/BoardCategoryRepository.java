package com.project.githering.Board.BoardCategory.Repository;

import com.project.githering.Board.BoardCategory.Entity.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {

    //CREATE
    @Override
    <S extends BoardCategory> S save(S entity);

    //DELETE
    void deleteByCategoryId(Long categoryId);

    //READ
    Optional<BoardCategory> findByCategoryId(Long categoryId);

    boolean existsByGroupIdAndCategoryName(Long groupId, String categoryName);

    List<BoardCategory> findAllByGroupId(Long groupId);
}
