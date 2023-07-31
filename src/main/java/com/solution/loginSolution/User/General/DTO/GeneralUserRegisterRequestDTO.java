package com.solution.loginSolution.User.General.DTO;

import com.solution.loginSolution.User.General.Entity.Role;
import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.General.Enum.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralUserRegisterRequestDTO {
    @NotBlank
    @Email
    private String userEmail;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public GeneralUser toEntity() {
        return GeneralUser.builder()
                .userType(UserType.NORMAL)
                .userName(userName)
                .userEmail(userEmail)
                .userPassword(password)
                .role(Role.ROLE_MEMBER)
                .build();
    }
}
