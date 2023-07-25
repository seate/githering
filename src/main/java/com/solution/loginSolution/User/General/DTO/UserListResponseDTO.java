package com.solution.loginSolution.User.General.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserListResponseDTO {
    @NotBlank
    private String userEmail;

    private List<UserInformResponseDTO> userList;
}
