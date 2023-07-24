package com.solution.loginSolution.User.Normal.DTO;

import com.solution.loginSolution.User.Normal.Entity.Role;
import com.solution.loginSolution.User.Normal.Entity.NormalUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequestDTO {
    @NotBlank
    private String userEmail;

    @NotBlank
    private String password;

    public NormalUser toEntity(String encodedPassword) {
        return NormalUser.builder()
                .userEmail(userEmail)
                .userPassword(encodedPassword)
                .role(Role.ROLE_MEMBER)
                .build();
    }
}
