package com.solution.loginSolution.User.General.DTO;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInformResponseDTO { //TODO userInform 정보 추가
    @NotBlank
    private String userEmail;

    public UserInformResponseDTO(GeneralUser generalUser) {
        this.userEmail = generalUser.getUserEmail();
    }
}
