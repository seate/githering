package com.project.githering.Admin.DTO;

import com.project.githering.User.General.Entity.GeneralUser;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminGeneralUserInformDTO {

    @NotBlank
    private Long userId;


    @NotBlank
    private String userName;

    @NotBlank
    private String loginUser;


    public AdminGeneralUserInformDTO(GeneralUser generalUser) {
        this.userId = generalUser.getId();
        this.userName = generalUser.getUserName();
        this.loginUser = generalUser.getLoginUser();
    }
}
