package com.project.githering.Group.DTO;

import com.project.githering.Group.Enum.GroupType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGroupInformRequestDTO {

    @NotBlank
    private Long groupId;

    @NotBlank
    private Long newGroupMasterId;

    @NotBlank
    private GroupType groupType;

    @NotBlank
    private String groupName;

    @NotBlank
    private String groupDescription;
}
