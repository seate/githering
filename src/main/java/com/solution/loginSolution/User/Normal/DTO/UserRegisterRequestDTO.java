package com.solution.loginSolution.User.Normal.DTO;

import com.solution.loginSolution.User.Normal.Entity.Role;
import com.solution.loginSolution.User.Normal.Entity.NormalUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDTO {
    @NotBlank
    @Email
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
