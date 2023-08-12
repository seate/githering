package com.project.githering.Board.BoardCategory.Service;

import com.project.githering.Board.BoardCategory.DTO.DeleteBoardCategoryRequestDTO;
import com.project.githering.Board.BoardCategory.DTO.FindAllBoardCategoryRequestDTO;
import com.project.githering.Board.BoardCategory.Entity.BoardCategory;
import com.project.githering.Board.BoardCategory.Exception.BoardCategoryExistException;
import com.project.githering.Board.BoardCategory.Exception.BoardCategoryNotExistException;
import com.project.githering.Board.BoardCategory.Repository.BoardCategoryRepository;
import com.project.githering.Board.BoardCategory.DTO.CreateBoardCategoryRequestDTO;
import com.project.githering.Board.BoardCategory.DTO.UpdateBoardCategoryRequestDTO;
import com.project.githering.Group.Exception.NoAuthorityException;
import com.project.githering.Group.Service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardCategoryServiceImpl implements BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    private final GroupService groupService;

    private void validateAuthority(Long userId, Long groupId) {
        if (!groupService.hasMasterAuthority(userId, groupId))
            throw new NoAuthorityException();
    }

    //CREATE
    @Override
    @Transactional
    public Long saveCategory(Long userId, CreateBoardCategoryRequestDTO createBoardCategoryRequestDTO) throws NoAuthorityException, BoardCategoryExistException {
        Long groupId = createBoardCategoryRequestDTO.getGroupId();
        validateAuthority(userId, groupId);

        if (boardCategoryRepository.existsByGroupIdAndCategoryName(groupId, createBoardCategoryRequestDTO.getCategoryName()))
            throw new BoardCategoryExistException("같은 이름의 카테고리가 이미 존재합니다.");


        return boardCategoryRepository.save(createBoardCategoryRequestDTO.toEntity()).getCategoryId();
    }

    //DELETE
    @Override
    @Transactional
    public void deleteCategory(Long userId, DeleteBoardCategoryRequestDTO deleteBoardCategoryRequestDTO) {
        BoardCategory boardCategory = boardCategoryRepository.findByCategoryId(deleteBoardCategoryRequestDTO.getCategoryId())
                .orElseThrow(BoardCategoryNotExistException::new);

        validateAuthority(userId, boardCategory.getGroupId());

        //TODO 카테고리 내에 있는 글들을 모두 삭제 or 이동해야함

        boardCategoryRepository.delete(boardCategory);
    }

    @Override
    public String findCategoryNameByCategoryId(Long categoryId) {
        return boardCategoryRepository.findByCategoryId(categoryId)
                .orElseThrow(BoardCategoryNotExistException::new).getCategoryName();
    }

    //READ
    @Override
    public List<BoardCategory> findAllCategoryByGroupId(FindAllBoardCategoryRequestDTO findAllBoardCategoryRequestDTO) {
        return boardCategoryRepository.findAllByGroupId(findAllBoardCategoryRequestDTO.getGroupId());
    }

    //UPDATE
    @Override
    @Transactional
    public void updateCategory(Long userId, UpdateBoardCategoryRequestDTO updateBoardCategoryRequestDTO) {
        Long categoryId = updateBoardCategoryRequestDTO.getCategoryId();

        BoardCategory boardCategory = boardCategoryRepository.findByCategoryId(categoryId)
                .orElseThrow(BoardCategoryNotExistException::new);

        validateAuthority(userId, boardCategory.getGroupId());

        boardCategory.setCategoryName(updateBoardCategoryRequestDTO.getCategoryName());
    }
}
