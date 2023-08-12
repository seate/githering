package com.project.githering.Board.BoardCategory.Controller;

import com.project.githering.Board.BoardCategory.DTO.*;
import com.project.githering.Board.BoardCategory.Service.BoardCategoryService;
import com.project.githering.User.General.Service.GeneralUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/BoardCategory")
public class BoardCategoryController {

    private final BoardCategoryService boardCategoryService;

    private final GeneralUserService generalUserService;

    //CREATE
    @PostMapping
    public ResponseEntity<Long> createBoardCategory(@RequestBody @Valid CreateBoardCategoryRequestDTO createBoardCategoryRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        return new ResponseEntity<>(boardCategoryService.saveCategory(userId, createBoardCategoryRequestDTO), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping
    public ResponseEntity<Void> deleteBoardCategory(@RequestBody @Valid DeleteBoardCategoryRequestDTO deleteBoardCategoryRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        boardCategoryService.deleteCategory(userId, deleteBoardCategoryRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //READ
    @GetMapping
    public ResponseEntity<List<BoardCategoryResponseDTO>> findAllByGroupId(@RequestBody @Valid FindAllBoardCategoryRequestDTO findAllBoardCategoryRequestDTO) {
        List<BoardCategoryResponseDTO> boardCategoryResponseDTOS = boardCategoryService
                .findAllCategoryByGroupId(findAllBoardCategoryRequestDTO)
                .stream().map(BoardCategoryResponseDTO::new)
                .toList();

        return new ResponseEntity<>(boardCategoryResponseDTOS, HttpStatus.OK);
    }

    //UPDATE
    @PatchMapping
    public ResponseEntity<Void> updateBoardCategory(@RequestBody @Valid UpdateBoardCategoryRequestDTO updateBoardCategoryRequestDTO) {
        Long userId = generalUserService.findIdByAuthentication();
        boardCategoryService.updateCategory(userId, updateBoardCategoryRequestDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

