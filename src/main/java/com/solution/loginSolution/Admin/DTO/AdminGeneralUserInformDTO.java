package com.solution.loginSolution.Admin.DTO;

import com.solution.loginSolution.User.General.Entity.GeneralUser;
import com.solution.loginSolution.User.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminGeneralUserInformDTO {

    @NotBlank
    private Long userId;

    @NotBlank
    private UserType userType;

    @NotBlank
    private String userName;

    @NotBlank
    private String userEmail;


    public AdminGeneralUserInformDTO(GeneralUser generalUser) {
        this.userId = generalUser.getId();
        this.userType = generalUser.getUserType();
        this.userName = generalUser.getUserName();
        this.userEmail = generalUser.getUserEmail();
    }
}
