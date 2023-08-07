package com.project.githering.Group.DTO;

import com.project.githering.Group.Enum.GroupType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGroupInformRequestDTO {

    @NotNull
    private Long groupId;

    @NotNull
    private Long newGroupMasterId;

    private GroupType groupType;

    @NotBlank
    private String groupName;

    @NotBlank
    private String groupDescription;
}
