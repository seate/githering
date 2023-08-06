package com.project.githering.Group.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGroupMasterRequestDTO {

    @NotBlank
    private Long groupId;

    @NotBlank
    private Long newGroupMasterId;
}
