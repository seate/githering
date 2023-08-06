package com.project.githering.Board.BoardCategory.Service;

import com.project.githering.Board.BoardCategory.DTO.DeleteBoardCategoryRequestDTO;
import com.project.githering.Board.BoardCategory.DTO.FindAllBoardCategoryRequestDTO;
import com.project.githering.Board.BoardCategory.Entity.BoardCategory;
import com.project.githering.Board.BoardCategory.DTO.CreateBoardCategoryRequestDTO;
import com.project.githering.Board.BoardCategory.DTO.UpdateBoardCategoryRequestDTO;

import java.util.List;

public interface BoardCategoryService {

    void saveCategory(Long userId, CreateBoardCategoryRequestDTO createBoardCategoryRequestDTO);

    void deleteCategory(Long userId, DeleteBoardCategoryRequestDTO deleteBoardCategoryRequestDTO);

    List<BoardCategory> findAllCategoryByGroupId(FindAllBoardCategoryRequestDTO findAllBoardCategoryRequestDTO);

    void updateCategory(Long userId, UpdateBoardCategoryRequestDTO updateBoardCategoryRequestDTO);
}
