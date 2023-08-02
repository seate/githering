package com.project.githering.User.General.DTO;

import com.project.githering.User.General.Entity.GeneralUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralUserInformResponseDTO {

    @NotBlank
    private String loginUser;

    @NotBlank
    private String userName;


    public GeneralUserInformResponseDTO(GeneralUser generalUser) {
        this.loginUser = generalUser.getLoginUser();
        this.userName = generalUser.getUserName();
    }
}
