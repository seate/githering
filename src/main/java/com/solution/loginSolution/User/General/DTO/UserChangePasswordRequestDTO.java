package com.solution.loginSolution.User.General.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserChangePasswordRequestDTO {
    @NotBlank
    private String newPassword;
}
