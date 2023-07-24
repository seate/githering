package com.solution.loginSolution.User.Normal.DTO;

import com.solution.loginSolution.User.Normal.Entity.NormalUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInformResponseDTO { //TODO userInform 정보 추가
    @NotBlank
    private String userEmail;

    public UserInformResponseDTO(NormalUser normalUser) {
        this.userEmail = normalUser.getUserEmail();
    }
}
