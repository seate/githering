package com.solution.loginSolution.User.General.DTO;

import com.solution.loginSolution.User.General.Entity.Role;
import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.Enums.UserType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralUserLoginRequestDTO {
    @NotBlank
    private String userEmail;

    @NotBlank
    private String password;

    public GeneralUser toEntity(String encodedPassword) {
        return GeneralUser.builder()
                .userType(UserType.NORMAL)
                .userEmail(userEmail)
                .userPassword(encodedPassword)
                .role(Role.ROLE_MEMBER)
                .build();
    }
}
