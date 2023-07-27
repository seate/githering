package com.solution.loginSolution.User.General.DTO;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralUserInformResponseDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String userEmail;

    public GeneralUserInformResponseDTO(GeneralUser generalUser) {
        this.userName = generalUser.getUserName();
        this.userEmail = generalUser.getUserEmail();
    }
}
